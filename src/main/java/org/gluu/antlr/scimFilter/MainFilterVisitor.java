/*
 * antlr-scim-filter is available under the MIT License (2008). See http://opensource.org/licenses/MIT for full text.
 *
 * Copyright (c) 2016, Gluu
 */
package org.gluu.antlr.scimFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Converts input filter to LDAP string filter format
 *
 * @author Val Pecaoco
 */
public class MainFilterVisitor extends ScimFilterBaseVisitor<String> {

    Logger logger = LoggerFactory.getLogger(MainFilterVisitor.class);

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public String visitScimFilter(ScimFilterParser.ScimFilterContext ctx) {

        // logger.info(">>>>> In visitScimFilter...");

        StringBuilder result = new StringBuilder("");
        result.append("(");
        for (ScimFilterParser.ExpressionContext expressionContext : ctx.expression()) {
            result.append(visit(expressionContext));
        }
        result.append(")");

        return result.toString();
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public String visitLPAREN_EXPR_RPAREN(ScimFilterParser.LPAREN_EXPR_RPARENContext ctx) {

        // logger.info(">>>>> In visitLPAREN_EXPR_RPAREN...");

        StringBuilder result = new StringBuilder("");
        result.append(ctx.LPAREN().getText());
        result.append(visit(ctx.expression()));
        result.append(ctx.RPAREN().getText());

        return result.toString();
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public String visitNOT_EXPR(ScimFilterParser.NOT_EXPRContext ctx) {

        // logger.info(">>>>> In visitNOT_EXPR...");

        StringBuilder result = new StringBuilder("");
        result.append("(!(");
        result.append(visit(ctx.expression()));
        result.append("))");

        return result.toString();
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public String visitEXPR_AND_EXPR(ScimFilterParser.EXPR_AND_EXPRContext ctx) {

        // logger.info(">>>>> In visitEXPR_AND_EXPR...");

        StringBuilder result = new StringBuilder("");
        result.append("((");
        result.append(visit(ctx.expression(0)));
        result.append(")");
        result.append("(");
        result.append(visit(ctx.expression(1)));
        result.append("))");

        return result.toString();
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public String visitEXPR_OR_EXPR(ScimFilterParser.EXPR_OR_EXPRContext ctx) {

        // logger.info(">>>>> In visitEXPR_OR_EXPR...");

        StringBuilder result = new StringBuilder("");
        result.append("(|(");
        result.append(visit(ctx.expression(0)));
        result.append(")");
        result.append("(");
        result.append(visit(ctx.expression(1)));
        result.append("))");

        return "";
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public String visitEXPR_COMP_EXPR(ScimFilterParser.EXPR_COMP_EXPRContext ctx) {

        // logger.info(">>>>> In visitEXPR_COMP_EXPR...");

        StringBuilder result = new StringBuilder("");
        result.append("((");
        result.append(visit(ctx.expression(0)));
        result.append(") ");
        result.append(visit(ctx.comparator()));
        result.append(" (");
        result.append(visit(ctx.expression(1)));
        result.append("))");

        return result.toString();
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public String visitATTR_COMP_CRITERIA(ScimFilterParser.ATTR_COMP_CRITERIAContext ctx) {

        // logger.info(">>>>> In visitATTR_COMP_CRITERIA...");

        StringBuilder result = new StringBuilder("");
        result.append("(");
        result.append(ctx.ATTRNAME().getText());
        result.append(" ");
        result.append(visit(ctx.comparator()));
        result.append(" ");
        result.append(visit(ctx.criteria()));
        result.append(")");

        return result.toString();
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public String visitATTR_COMP_EXPR(ScimFilterParser.ATTR_COMP_EXPRContext ctx) {

        // logger.info(">>>>> In visitATTR_COMP_EXPR...");

        StringBuilder result = new StringBuilder("");
        result.append("(");
        result.append(ctx.ATTRNAME().getText());
        result.append(" ");
        result.append(visit(ctx.comparator()));
        result.append(" ");
        result.append(visit(ctx.expression()));
        result.append(")");

        return result.toString();
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public String visitATTR_PR(ScimFilterParser.ATTR_PRContext ctx) {

        // logger.info(">>>>> In visitATTR_PR...");

        StringBuilder result = new StringBuilder("");
        result.append("(");
        result.append(ctx.ATTRNAME().getText());
        result.append("=*)");

        return result.toString();
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public String visitComparator(ScimFilterParser.ComparatorContext ctx) {
        // logger.info(">>>>> In visitComparator...");
        return ctx.getText();
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation returns the result of calling
     * {@link #visitChildren} on {@code ctx}.</p>
     *
     * @param ctx
     */
    @Override
    public String visitCriteria(ScimFilterParser.CriteriaContext ctx) {
        // logger.info(">>>>> In visitCriteria...");
        return ctx.getText();
    }
}
