/*
 * antlr-scim-filter is available under the MIT License (2008). See http://opensource.org/licenses/MIT for full text.
 *
 * Copyright (c) 2016, Gluu
 */
package org.gluu.antlr.scimFilter;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.gluu.antlr.scimFilter.exception.ScimFilterErrorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Val Pecaoco
 */
public class MainFilterParser {

    Logger logger = LoggerFactory.getLogger(MainFilterParser.class);

    public String visitTree(String filter) throws Exception {

        ParseTree parseTree = getParser(filter).scimFilter();

        // Visit tree
        MainFilterVisitor mainFilterVisitor = new MainFilterVisitor();
        String result = mainFilterVisitor.visit(parseTree);

        return result;
    }

    private ScimFilterParser getParser(String filter) throws Exception {

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

        return parser;
    }
}
