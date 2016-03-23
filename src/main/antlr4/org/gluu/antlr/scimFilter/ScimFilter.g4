/*
 * antlr-scim-filter is available under the MIT License (2008). See http://opensource.org/licenses/MIT for full text.
 *
 * Copyright (c) 2016, Gluu
 *
 * Author: Val Pecaoco
 */
grammar ScimFilter;

options
{
  language = Java;
}

scimFilter
 : expression* EOF
 ;

expression
 : ATTRNAME comparator criteria      # ATTR_COMP_CRITERIA
 | ATTRNAME comparator expression    # ATTR_COMP_EXPR
 | ATTRNAME PR                       # ATTR_PR
 | NOT expression                    # NOT_EXPR
 | expression AND expression         # EXPR_AND_EXPR
 | expression OR expression          # EXPR_OR_EXPR
 | expression comparator expression  # EXPR_COMP_EXPR
 | LPAREN expression RPAREN          # LPAREN_EXPR_RPAREN
 ;

criteria : DELIMETER (.)+? DELIMETER;

comparator
 : 'eq' | 'ne' | 'co' | 'sq' | 'ew' | 'gt' | 'lt' | 'ge' | 'le'
 ;

NOT : 'not';

AND : 'and';
OR  : 'or';

PR : 'pr';

LPAREN : '(';
RPAREN : ')';

ATTRNAME  : ('-' | '_' | 'A'..'Z' | 'a'..'z' | '0'..'9' | '.')+;

DELIMETER : '"';

WS : ('\t' | ' ' | '\r' | '\n'| '\u000C')+ -> skip;
