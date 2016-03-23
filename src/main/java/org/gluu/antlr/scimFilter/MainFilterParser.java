/*
 * antlr-scim-filter is available under the MIT License (2008). See http://opensource.org/licenses/MIT for full text.
 *
 * Copyright (c) 2016, Gluu
 */
package org.gluu.antlr.scimFilter;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.gluu.antlr.scimFilter.exception.ScimFilterErrorHandler;

/**
 * @author Val Pecaoco
 */
public class MainFilterParser {

    public void parse(String filter) throws Exception {

        // Get lexer
        ANTLRInputStream input = new ANTLRInputStream(filter);
        ScimFilterLexer lexer = new ScimFilterLexer(input);

        // Get list of matched tokens
        CommonTokenStream tokens = new CommonTokenStream(lexer);

        // Pass tokens to the parser
        ScimFilterParser parser = new ScimFilterParser(tokens);
        parser.setBuildParseTree(true);
        parser.setTrimParseTree(true);
        parser.setProfile(true);
        parser.removeErrorListeners();
        parser.setErrorHandler(new ScimFilterErrorHandler());

        ParserRuleContext ruleContext = parser.scimFilter();

        if (ruleContext.exception != null) {
            throw ruleContext.exception;
        }

        // Walk tree
        ParseTreeWalker.DEFAULT.walk(new MainFilterListener(), ruleContext);
    }
}
