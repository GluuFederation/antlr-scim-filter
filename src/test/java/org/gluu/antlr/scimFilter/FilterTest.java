/*
 * antlr-scim-filter is available under the MIT License (2008). See http://opensource.org/licenses/MIT for full text.
 *
 * Copyright (c) 2016, Gluu
 */
package org.gluu.antlr.scimFilter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collection;

/**
 * @author Val Pecaoco
 */
@RunWith(Parameterized.class)
public class FilterTest {

    Logger logger = LoggerFactory.getLogger(FilterTest.class);

    @Parameterized.Parameters
    public static Collection<Object[]> testData() {

        return Arrays.asList(new Object[][] {
            // Valid
            // { true, "userType eq \"emp\"loy\"ee\"" },
            // { true, "userType eq \"employee\"" },
            { true, "userType1234 ne \"Employee\" and email.type eq \"work\"" },
            // { true, "(userType1234 ne \"Employee\") or (email.type eq \"work\")" },
            { true, "userType pr" },
            // { true, "userType pr and not (userType eq \"employee\")" },
            // { true, "not userType eq \"employee\"" },
            // { true, "not (userType eq \"employee\")" },
            // { true, "userType eq \"employee\" and (email.type eq \"Work\" or email-type eq \"personal\")" },
            // { true, "userType pr and ((type eq \"work\" or type eq \"personal\") and user_Type eq \"employee\")" },
            { true, "userType eq \"employee\" and userType pr" }
            // { true, "userType pr and userType eq \"employee\"" },
            // { true, "(userType pr) and ((type eq \"work\" or type eq \"personal\") and userType eq \"employee\")" },
            // { true, "userType pr and (type eq \"work\" or type eq \"personal\") and userType eq \"employee\"" },
            // Invalid
            /*
            { true, "user\"Type pr" },
            { true, "pr userType" },
            { true, "pr userType pr" },
            { true, "not userType" },
            { true, "not userType not" }
            /*/
        });
    }

    private final boolean isValid;
    private final String testFilter;

    public FilterTest(boolean isValid, String testFilter) {
        this.isValid = isValid;
        this.testFilter = testFilter;
    }

    @Test
    public void testFilter() throws Exception {

        MainFilterParser mainFilterParser = new MainFilterParser();

        // Walk tree
        // mainFilterParser.walkTree(this.testFilter);

        // Visit tree
        String result = mainFilterParser.visitTree(this.testFilter);

        logger.info("##### test filter = " + this.testFilter);
        logger.info(">>>>> parsed result = " + result);

        assert(this.isValid);
    }
}
