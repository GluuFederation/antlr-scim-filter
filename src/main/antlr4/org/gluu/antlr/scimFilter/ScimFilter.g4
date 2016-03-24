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
 : LPAREN expression RPAREN        # LPAREN_EXPR_RPAREN
 | NOT expression                  # NOT_EXPR
 | expression AND expression       # EXPR_AND_EXPR
 | expression OR expression        # EXPR_OR_EXPR
 | expression operator expression  # EXPR_OPER_EXPR
 | ATTRNAME operator criteria      # ATTR_OPER_CRITERIA
 | ATTRNAME operator expression    # ATTR_OPER_EXPR
 | ATTRNAME PR                     # ATTR_PR
 ;

criteria : STRING;

STRING : '"' (ESC | ~[\\])+? '"';
fragment ESC : '\\' (["\\/bfnrt] | UNICODE);
fragment UNICODE : 'u' HEX HEX HEX HEX;
fragment HEX : [0-9a-fA-F];

operator
 : 'eq' | 'ne' | 'co' | 'sw' | 'ew' | 'gt' | 'lt' | 'ge' | 'le'
 ;

NOT : 'not';

AND : 'and';
OR  : 'or';

PR : 'pr';

LPAREN : '(';
RPAREN : ')';

ATTRNAME : ('-' | '_' | 'A'..'Z' | 'a'..'z' | '0'..'9' | '.')+;

WS : ('\t' | ' ' | '\r' | '\n'| '\u000C')+ -> skip;
