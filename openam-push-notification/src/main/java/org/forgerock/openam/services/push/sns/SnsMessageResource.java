/*
* The contents of this file are subject to the terms of the Common Development and
* Distribution License (the License). You may not use this file except in compliance with the
* License.
*
* You can obtain a copy of the License at legal/CDDLv1.0.txt. See the License for the
* specific language governing permission and limitations under the License.
*
* When distributing Covered Software, include this CDDL Header Notice in each file and include
* the License file at legal/CDDLv1.0.txt. If applicable, add the following below the CDDL
* Header, with the fields enclosed by brackets [] replaced by your own identifying
* information: "Portions copyright [year] [name of copyright owner]".
*
* Copyright 2016 ForgeRock AS.
*/
package org.forgerock.openam.services.push.sns;

import static org.forgerock.json.JsonValue.*;
import static org.forgerock.json.resource.Responses.*;
import static org.forgerock.openam.services.push.PushNotificationConstants.*;
import static org.forgerock.util.promise.Promises.*;

import com.sun.identity.shared.debug.Debug;
import java.util.Map;
import javax.inject.Named;
import org.forgerock.json.JsonValue;
import org.forgerock.json.jose.common.JwtReconstruction;
import org.forgerock.json.jose.jws.SignedJwt;
import org.forgerock.json.jose.jwt.Jwt;
import org.forgerock.json.resource.ActionRequest;
import org.forgerock.json.resource.ActionResponse;
import org.forgerock.json.resource.NotFoundException;
import org.forgerock.json.resource.ResourceException;
import org.forgerock.json.resource.annotations.Action;
import org.forgerock.json.resource.annotations.RequestHandler;
import org.forgerock.openam.cts.CTSPersistentStore;
import org.forgerock.openam.cts.api.tokens.Token;
import org.forgerock.openam.cts.exceptions.CoreTokenException;
import org.forgerock.openam.cts.utils.JSONSerialisation;
import org.forgerock.openam.rest.RealmContext;
import org.forgerock.openam.rest.RestUtils;
import org.forgerock.openam.services.push.dispatch.MessageDispatcher;
import org.forgerock.openam.services.push.dispatch.Predicate;
import org.forgerock.openam.services.push.dispatch.PredicateNotMetException;
import org.forgerock.openam.tokens.CoreTokenField;
import org.forgerock.openam.utils.JsonValueBuilder;
import org.forgerock.services.context.Context;
import org.forgerock.util.Reject;
import org.forgerock.util.promise.Promise;

/**
 * An endpoint, created and attached to the Router at the point of a new {@link SnsHttpDelegate} being
 * configured to accept inbound messages from a remote device over SNS. Converts messages that come in
 * from an SNS-specific format into a generalised one that the Push auth system and message dispatcher can use.
 *
 * The registration field is special, as it communicates out with Amazon's SNS to ensure that the registration
 * is successfully completed.
 *
 * This endpoint will receive both authentication and registration messages. The format of these messages will be:
 *
 * {
 *     "jwt" : "...", // signed jwt, including all claims necessary for operation (reg or auth)
 *     "messageId" : "..." // unique identifier for this message
 * }
 *
 * {@see SnsHttpDelegate}.
 * {@see PushNotificationService}.
 */
@RequestHandler
public class SnsMessageResource {

    private final MessageDispatcher messageDispatcher;
    private final Debug debug;
    private final CTSPersistentStore coreTokenService;
    private final JSONSerialisation jsonSerialisation;

    /**
     * Generate a new SnsMessageResource using the provided MessageDispatcher.
     * @param coreTokenService A copy of the core token services - messages are dropped on to this for use in clustered
     *                         environments.
     * @param messageDispatcher Used to deliver messages received at this endpoint to their appropriate locations
     *                          within OpenAM.
     * @param jsonSerialisation Used to perform the serialisation necessary for inserting tokens into the CTS.
     * @param debug For writing out debug messages.
     */
    public SnsMessageResource(CTSPersistentStore coreTokenService, MessageDispatcher messageDispatcher,
                              JSONSerialisation jsonSerialisation, @Named("frPush") Debug debug) {
        this.messageDispatcher = messageDispatcher;
        this.jsonSerialisation = jsonSerialisation;
        this.debug = debug;
        this.coreTokenService = coreTokenService;
    }

    /**
     * Forwards the provided message - so long as it has a messageId in the appropriate place - to the
     * messageDispatcher which is responsible for informing the appropriate receiver in OpenAM of the
     * data received in the message.
     *
     * @param context Context of this request used to retrieve the current realm location.
     * @param actionRequest The request triggering the dispatch.
     * @return An empty HTTP 200 if everything was okay, or an HTTP 400 if the request was malformed.
     */
    @Action
    public Promise<ActionResponse, ResourceException> authenticate(Context context, ActionRequest actionRequest) {
        return handle(context, actionRequest);
    }

    /**
     * For a registration message - determines the type of remote OS and registers the device
     * with Amazon SNS. Then returns information necessary to continue registration back to the
     * message dispatcher so the calling method can store the newly minted device
     * and continue the authentication process.
     *
     * @param context Context of this request used to retrieve the current realm location.
     * @param actionRequest The request triggering the dispatch.
     * @return An empty HTTP 200 if everything was okay, or an HTTP 400 if the request was malformed.
     */
    @Action
    public Promise<ActionResponse, ResourceException> register(Context context, ActionRequest actionRequest) {
        return handle(context, actionRequest);
    }

    private Promise<ActionResponse, ResourceException> handle(Context context, ActionRequest actionRequest) {
        Reject.ifFalse(context.containsContext(RealmContext.class));

        final JsonValue actionContent = actionRequest.getContent();

        String realm = context.asContext(RealmContext.class).getResolvedRealm();
        JsonValue messageIdLoc = actionContent.get(MESSAGE_ID_JSON_POINTER);
        String messageId;

        if (messageIdLoc == null) {
            debug.warning("Received message in realm {} with invalid messageId.", realm);
            return RestUtils.generateBadRequestException();
        } else {
            messageId = messageIdLoc.asString();
        }

        try {
            messageDispatcher.handle(messageId, actionContent);
        } catch (NotFoundException e) {
            debug.warning("Unable to deliver message with messageId {} in realm {}.", messageId, realm, e);
            try {
                attemptFromCTS(messageId, actionContent);
            } catch (IllegalAccessException | InstantiationException | ClassNotFoundException
                    | CoreTokenException | NotFoundException ex) {
                debug.warning("Nothing in the CTS with messageId {}.", messageId, ex);
                return RestUtils.generateBadRequestException();
            }
            return RestUtils.generateBadRequestException();
        } catch (PredicateNotMetException e) {
            debug.warning("Unable to deliver message with messageId {} in realm {} as predicate not met.",
                    messageId, realm, e);
            return RestUtils.generateBadRequestException();
        }

        return newResultPromise(newActionResponse(json(object())));
    }

    /**
     * For the in-memory equivalent, {@link MessageDispatcher#handle(String, JsonValue)}.
     */
    private boolean attemptFromCTS(String messageId, JsonValue actionContent)
            throws CoreTokenException, ClassNotFoundException, IllegalAccessException, InstantiationException,
            NotFoundException {
        Token coreToken = coreTokenService.read(messageId);

        if (coreToken == null) {
            throw new NotFoundException("Unable to find token with id " + messageId + " in CTS.");
        }

        byte[] serializedBlob = coreToken.getBlob();
        String fromBlob = new String(serializedBlob);

        JsonValue jsonValue = JsonValueBuilder.toJsonValue(fromBlob);
        Map<String, Object> predicates = jsonValue.asMap();

        for (Map.Entry<String, Object> entry : predicates.entrySet()) {
            String className = entry.getKey();
            Predicate pred =
                    (Predicate) jsonSerialisation.deserialise((String) entry.getValue(), Class.forName(className));
            if (!pred.perform(actionContent)) {
                return false;
            }
        }

        addDeny(coreToken, actionContent);

        coreTokenService.update(coreToken);

        return true;
    }

    private void addDeny(Token coreToken, JsonValue actionContent) {

        Jwt possibleDeny = new JwtReconstruction().reconstructJwt(actionContent.get(JWT).asString(), SignedJwt.class);

        if (possibleDeny.getClaimsSet().getClaim(DENY_LOCATION) != null) {
            coreToken.setAttribute(CoreTokenField.INTEGER_ONE, DENY_VALUE);
        }

    }

}
