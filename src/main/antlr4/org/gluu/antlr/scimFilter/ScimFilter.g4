/*
 * antlr-scim-filter is available under the MIT License (2008). See http://opensource.org/licenses/MIT for full text.
 *
 * Copyright (c) 2016, Gluu
 * Author: Val Pecaoco
 */
grammar ScimFilter;

scimFilter
 : expression EOF
 ;

expression
 : LPAREN expression RPAREN
 | expression 'and' expression
 | expression 'or' expression
 | ATTRNAME comparator CRITERIA
 | ATTRNAME comparator expression
 | ATTRNAME 'pr'
 | expression ('and' | 'or') 'not' expression
 ;

comparator
 : 'eq' | 'ne' | 'co' | 'sq' | 'ew' | 'gt' | 'lt' | 'ge' | 'le'
 ;

LPAREN : '(';
RPAREN : ')';

ATTRNAME : ('-' | '_' | 'A'..'Z' | 'a'..'z' | '0'..'9' | '.')+;
CRITERIA : '"' (.)+? '"';

WS : ('\t' | ' ' | '\r' | '\n'| '\u000C')+ -> skip;