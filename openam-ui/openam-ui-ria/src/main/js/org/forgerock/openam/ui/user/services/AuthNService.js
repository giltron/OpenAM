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
 * Portions copyright 2011-2016 ForgeRock AS.
 */

define([
    "jquery",
    "lodash",
    "org/forgerock/commons/ui/common/main/AbstractDelegate",
    "org/forgerock/commons/ui/common/main/Configuration",
    "org/forgerock/openam/ui/common/util/Constants",
    "org/forgerock/commons/ui/common/util/CookieHelper",
    "org/forgerock/commons/ui/common/main/EventManager",
    "org/forgerock/commons/ui/common/components/Messages",
    "org/forgerock/openam/ui/common/util/RealmHelper"
], ($, _, AbstractDelegate, Configuration, Constants, CookieHelper, EventManager, Messages, RealmHelper) => {
    const obj = new AbstractDelegate(`${Constants.host}/${Constants.context}/json/`);
    let requirementList = [];
    // to be used to keep track of the attributes associated with whatever requirementList contains
    let knownAuth = {};

    obj.begin = function (options) {
        const args = {};

        knownAuth = _.clone(Configuration.globalData.auth);

        /**
         * args is the URL query string
         * Configuration.globalData.auth.urlParams is fragment query string
         */
        if (Configuration.globalData.auth.urlParams) {
            _.extend(args, Configuration.globalData.auth.urlParams);
        }

        // In case user has logged in already update session
        const tokenCookie = CookieHelper.getCookie(Configuration.globalData.auth.cookieName);
        if (tokenCookie) {
            args.sessionUpgradeSSOTokenId = tokenCookie;
        }

        const url = RealmHelper.decorateURIWithSubRealm("__subrealm__/authenticate");
        if (RealmHelper.getOverrideRealm()) {
            args.realm = RealmHelper.getOverrideRealm();
        }

        const serviceCall = {
            type: "POST",
            headers: { "Accept-API-Version": "protocol=1.0,resource=2.0" },
            data: "",
            url: `${url}?${$.param(args)}`,
            errorsHandlers: {
                "unauthorized": { status: "401" },
                "bad request": { status: "400" }
            }
        };

        _.assign(serviceCall, options);

        return obj.serviceCall(serviceCall).then((requirements) => requirements,

            (jqXHR) => {
                // some auth processes might throw an error fail immediately
                const errorBody = $.parseJSON(jqXHR.responseText);

                // if the error body contains an authId, then we might be able to
                // continue on after this error to the next module in the chain
                if (errorBody.hasOwnProperty("authId")) {
                    return obj.submitRequirements(errorBody)
                        .then((requirements) => {
                            obj.resetProcess();
                            return requirements;
                        }, () => errorBody
                    );
                } else if (errorBody.code && errorBody.code === 400) {
                    return {
                        message: errorBody.message,
                        type: Messages.TYPE_DANGER
                    };
                }
                return errorBody;
            });
    };

    obj.handleRequirements = function (requirements) {
        //callbackTracking allows us to determine if we're expecting to return having gone away
        function callbackTracking (callback) {
            return callback.type === "RedirectCallback" && _.find(callback.output, {
                name: "trackingCookie",
                value: true
            });
        }

        if (requirements.hasOwnProperty("authId")) {
            requirementList.push(requirements);
            Configuration.globalData.auth.currentStage = requirementList.length;

            if (!CookieHelper.getCookie("authId") && _.find(requirements.callbacks, callbackTracking)) {
                CookieHelper.setCookie(
                    "authId",
                    requirements.authId, "", "/",
                    Configuration.globalData.auth.cookieDomains,
                    Configuration.globalData.secureCookie);
            }
        } else if (requirements.hasOwnProperty("tokenId")) {
            CookieHelper.setCookie(
                Configuration.globalData.auth.cookieName,
                requirements.tokenId, "", "/",
                Configuration.globalData.auth.cookieDomains,
                Configuration.globalData.secureCookie);
        }
    };

    obj.submitRequirements = function (requirements, options) {
        const processSucceeded = (requirements) => {
            obj.handleRequirements(requirements);
            return requirements;
        };
        const processFailed = (reason) => {
            const failedStage = requirementList.length;
            obj.resetProcess();
            return [failedStage, reason];
        };
        const goToFailureUrl = (errorBody) => {
            if (errorBody.detail && errorBody.detail.failureUrl) {
                console.log(errorBody.detail.failureUrl);
                window.location.href = errorBody.detail.failureUrl;
            }
        };
        const url = RealmHelper.decorateURIWithRealm("__subrealm__/authenticate");

        const serviceCall = {
            type: "POST",
            headers: { "Accept-API-Version": "protocol=1.0,resource=2.0" },
            data: JSON.stringify(requirements),
            url,
            errorsHandlers: {
                "unauthorized": { status: "401" },
                "timeout": { status: "408" },
                "Internal Server Error ": { status: "500" }
            }
        };

        _.assign(serviceCall, options);

        return obj.serviceCall(serviceCall).then(processSucceeded, (jqXHR) => {
            var oldReqs,
                errorBody,
                currentStage = requirementList.length,
                message,
                reasonThatWillNotBeDisplayed = 1;
            if (jqXHR.status === 408) {
                // we timed out, so let's try again with a fresh session
                oldReqs = requirementList[0];
                obj.resetProcess();
                return obj.begin().then((requirements) => {
                    obj.handleRequirements(requirements);

                    if (requirements.hasOwnProperty("authId")) {
                        if (currentStage === 1) {
                            /**
                             * if we were at the first stage when the timeout occurred,
                             * try to do it again immediately.
                             */
                            oldReqs.authId = requirements.authId;
                            return obj.submitRequirements(oldReqs).then(processSucceeded, processFailed);
                        } else {
                            // restart the process at the beginning
                            EventManager.sendEvent(Constants.EVENT_DISPLAY_MESSAGE_REQUEST, "loginTimeout");
                            return requirements;
                        }
                    } else {
                        return requirements;
                    }
                /**
                 * this is very unlikely, since it would require a call to .begin() to fail
                 * after having succeeded once before
                 */
                }, processFailed);
            } else if (jqXHR.status === 500) {
                if (jqXHR.responseJSON && jqXHR.responseJSON.message) {
                    message = jqXHR.responseJSON.message;
                } else {
                    message = $.t("config.messages.CommonMessages.unknown");
                }
                Messages.addMessage({ message, type: Messages.TYPE_DANGER });
            } else { // we have a 401 unauthorized response
                errorBody = $.parseJSON(jqXHR.responseText);

                // if the error body has an authId property, then we may be
                // able to advance beyond this error
                if (errorBody.hasOwnProperty("authId")) {
                    return obj.submitRequirements(errorBody).then(processSucceeded, processFailed);
                } else {
                    obj.resetProcess();

                    Messages.addMessage({
                        message: errorBody.message,
                        type: Messages.TYPE_DANGER
                    });

                    goToFailureUrl(errorBody);

                    // The reason used here will not be translated into a common message and hence not displayed.
                    // This is so that only the message above is displayed.
                    return $.Deferred().reject(currentStage, reasonThatWillNotBeDisplayed).promise();
                }
            }
        });
    };

    obj.resetProcess = function () {
        requirementList = [];
    };

    function hasRealmChanged () {
        const auth = Configuration.globalData.auth;
        return auth.subRealm !== knownAuth.subRealm ||
            _.get(auth, "urlParams.realm") !== _.get(knownAuth, "urlParams.realm");
    }

    function hasAuthIndexChanged () {
        const auth = Configuration.globalData.auth;
        return _.get(auth, "urlParams.authIndexType") !== _.get(knownAuth, "urlParams.authIndexType") ||
            _.get(auth, "urlParams.authIndexValue") !== _.get(knownAuth, "urlParams.authIndexValue");
    }

    obj.getRequirements = function (args) {
        if (CookieHelper.getCookie("authId")) {
            return obj.submitRequirements(_.extend({ "authId": CookieHelper.getCookie("authId") },
                Configuration.globalData.auth.urlParams)).done(() => {
                    knownAuth = _.clone(Configuration.globalData.auth);
                    CookieHelper.deleteCookie("authId", "/", Configuration.globalData.auth.cookieDomains);
                });
        } else if (requirementList.length === 0 || hasRealmChanged() || hasAuthIndexChanged()) {
            obj.resetProcess();

            return obj.begin(args)
                .then((requirements) => {
                    obj.handleRequirements(requirements);
                    return requirements;
                }, (error) => error);
        } else {
            return $.Deferred().resolve(requirementList[requirementList.length - 1]);
        }
    };

    obj.setGoToUrl = function (tokenId, urlGoTo) {
        return obj.serviceCall({
            type: "POST",
            headers: { "Accept-API-Version": "protocol=1.0,resource=2.0" },
            data: JSON.stringify({ "goto": urlGoTo }),
            url: "",
            serviceUrl: `${Constants.host}/${Constants.context}/json/users?_action=validateGoto`,
            errorsHandlers: { "Bad Request": { status: "400" } }
        });
    };

    return obj;
});
