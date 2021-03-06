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
package org.forgerock.openam.sm.validators;

import static org.assertj.core.api.Assertions.*;

import java.util.Collections;
import org.forgerock.openam.sm.validation.PortValidator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class PortValidatorTest {

    PortValidator validator = new PortValidator();

    @DataProvider(name = "data")
    private Object[][] data() {
        return new Object[][]{
                {"NaN", false},
                {"0", false}, //0 is technically valid, but does not exist and is not supported by us
                {"1", true},
                {"65536", false},
                {"65535", true}
        };
    }

    @Test(dataProvider = "data")
    public void checkCorrectness(String underTest, boolean expected) {
        //given

        //when
        boolean result = validator.validate(Collections.singleton(underTest));

        //then
        assertThat(expected).isEqualTo(result);
    }

}

