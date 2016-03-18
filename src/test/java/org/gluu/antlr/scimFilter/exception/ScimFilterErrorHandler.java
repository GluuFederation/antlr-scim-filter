/*
 * antlr-scim-filter is available under the MIT License (2008). See http://opensource.org/licenses/MIT for full text.
 *
 * Copyright (c) 2016, Gluu
 */
package org.gluu.antlr.scimFilter.exception;

import org.antlr.v4.runtime.*;

/**
 * @author Val Pecaoco
 */
public class ScimFilterErrorHandler extends DefaultErrorStrategy {

    @Override
    public void recover(Parser recognizer, RecognitionException e) {
        throw e;
    }

    @Override
    public Token recoverInline(Parser recognizer) throws RecognitionException {
        throw new RuntimeException(new InputMismatchException(recognizer));
    }

    @Override
    public void sync(Parser recognizer) throws RecognitionException {
    }
}
