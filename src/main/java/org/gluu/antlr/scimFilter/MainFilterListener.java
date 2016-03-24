/*
 * antlr-scim-filter is available under the MIT License (2008). See http://opensource.org/licenses/MIT for full text.
 *
 * Copyright (c) 2016, Gluu
 */
package org.gluu.antlr.scimFilter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Val Pecaoco
 */
public class MainFilterListener extends ScimFilterBaseListener {

    Logger logger = LoggerFactory.getLogger(MainFilterListener.class);

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation does nothing.</p>
     *
     * @param ctx
     */
    @Override
    public void enterScimFilter(ScimFilterParser.ScimFilterContext ctx) {
        super.enterScimFilter(ctx);
        logger.info("----------");
        // logger.info("ctx.getText() = " + ctx.getText());
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation does nothing.</p>
     *
     * @param ctx
     */
    @Override
    public void exitScimFilter(ScimFilterParser.ScimFilterContext ctx) {
        super.exitScimFilter(ctx);
        // logger.info("ctx.getText() = " + ctx.getText());
        logger.info("----------");
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation does nothing.</p>
     *
     * @param ctx
     */
    @Override
    public void enterATTR_OPER_CRITERIA(ScimFilterParser.ATTR_OPER_CRITERIAContext ctx) {
        super.enterATTR_OPER_CRITERIA(ctx);
        logger.info("ATTRNAME = " + ctx.ATTRNAME().getText());
        logger.info("operator = " + ctx.operator().getText());
        logger.info("criteria = " + ctx.criteria().getText());
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation does nothing.</p>
     *
     * @param ctx
     */
    @Override
    public void enterATTR_OPER_EXPR(ScimFilterParser.ATTR_OPER_EXPRContext ctx) {
        super.enterATTR_OPER_EXPR(ctx);
        logger.info("ATTRNAME = " + ctx.ATTRNAME().getText());
        logger.info("operator = " + ctx.operator().getText());
        logger.info("expression = " + ctx.expression().getText());
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation does nothing.</p>
     *
     * @param ctx
     */
    @Override
    public void enterATTR_PR(ScimFilterParser.ATTR_PRContext ctx) {
        super.enterATTR_PR(ctx);
        logger.info("ATTRNAME = " + ctx.ATTRNAME().getText());
        logger.info("PR = " + ctx.PR().getText());
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation does nothing.</p>
     *
     * @param ctx
     */
    @Override
    public void enterEXPR_AND_EXPR(ScimFilterParser.EXPR_AND_EXPRContext ctx) {
        super.enterEXPR_AND_EXPR(ctx);
        logger.info("EXPR 1 = " + ctx.expression().get(0).getText());
        logger.info("AND = " + ctx.AND().getText());
        logger.info("EXPR 2 = " + ctx.expression().get(1).getText());
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation does nothing.</p>
     *
     * @param ctx
     */
    @Override
    public void enterEXPR_OR_EXPR(ScimFilterParser.EXPR_OR_EXPRContext ctx) {
        super.enterEXPR_OR_EXPR(ctx);
        logger.info("EXPR 1 = " + ctx.expression().get(0).getText());
        logger.info("OR = " + ctx.OR().getText());
        logger.info("EXPR 2 = " + ctx.expression().get(1).getText());
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation does nothing.</p>
     *
     * @param ctx
     */
    @Override
    public void enterNOT_EXPR(ScimFilterParser.NOT_EXPRContext ctx) {
        super.enterNOT_EXPR(ctx);
        logger.info("NOT = " + ctx.NOT().getText());
        logger.info("expression = " + ctx.expression().getText());
    }

    /**
     * {@inheritDoc}
     * <p>
     * <p>The default implementation does nothing.</p>
     *
     * @param ctx
     */
    @Override
    public void enterLPAREN_EXPR_RPAREN(ScimFilterParser.LPAREN_EXPR_RPARENContext ctx) {
        super.enterLPAREN_EXPR_RPAREN(ctx);
        logger.info("LPAREN = " + ctx.LPAREN().getText());
        logger.info("expression = " + ctx.expression().getText());
        logger.info("RPAREN = " + ctx.RPAREN().getText());
    }
}
