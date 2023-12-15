grammar Jython;

program : import_class* (classDef)? ;
import_class : 'import' CLASSNAME ;
classDef : 'class' class_name = CLASSNAME ('(' parent_name (',' parent_name)* ')')? '{' class_body* '}';

class_body : varDec | methodDec | constructor | arrayDec ;
varDec : varBase ;
arrayDec : name=(TYPE | CLASSNAME) '['INTEGER']' word ;
methodDec : 'def' (return_type=(TYPE | CLASSNAME)|'void') word '(' parameter* ')''{' ( statement)* '}';
constructor : 'def' name=(TYPE | CLASSNAME) '(' parameter* ')''{' ( statement)* '}' ;

parameter : varBase (',' varBase)* ;
statement : varDec | assignment | print_statement | method_call | return_statement
|if_statement | while_statement | if_else_statement | for_statement;
return_statement : 'return' exp ;
condition_list : condition (('or'|'and') condition)* ;
condition : BOOL | prefixexp | (exp) relational_operators (exp);
if_statement : 'if' '(' condition_list ')' '{' statement* '}';
while_statement : 'while' '(' condition_list ')' '{' statement* '}' ;
if_else_statement :'if' '(' condition_list ')' '{' statement* '}' ('elif' '(' condition_list ')' '{' statement* '}')* 'else' '{' statement* '}' ;
print_statement : 'print' '(' (prefixexp | (TYPE | CLASSNAME) args | INTEGER |STRING | BOOL) ')' ;
for_statement : 'for' word 'in' word '{' statement* '}'
| 'for' word 'in' 'range''('INTEGER (',' INTEGER)? (',' INTEGER)? ')' '{' statement* '}' ;
method_call : word ('.')? args ;
assignment : prefixexp assignment_operators exp
| varDec assignment_operators exp
| arrayDec '=' (TYPE | CLASSNAME) args ('['INTEGER']') ;
exp :'none' | BOOL | INTEGER | STRING | FLOAT | prefixexp | exp arithmetic_operator exp
| (TYPE | CLASSNAME) args | '('exp')' | word args ;
prefixexp : word | prefixexp '[' INTEGER ']' | prefixexp '.' word | prefixexp '.' word args ;
args : '(' (explist)? ')' ;
explist : exp (',' exp)*;
arithmetic_operator: '+' | '-' | '*' | '/' | '%' ;
relational_operators : '<' | '>' | '<=' | '>=' | '==' | '!=' ;
assignment_operators : '=' | '+=' | '-=' | '*=' | '/=' ;
parent_name: CLASSNAME;

varBase: name=(TYPE | CLASSNAME) word;
word: ID;

TYPE: INT | FLOATING_POINT | STRING | BOOL;
INT: 'int';
FLOATING_POINT: 'float';
STRING: 'string';
BOOL: 'bool';
CLASSNAME: [A-Z] (LETTER|DIGIT)*;
ID: [a-z]([A-Za-z_])*;
LETTER: [A-Za-z];
INTEGER: DIGIT+;
FLOAT: (DIGIT)+ '.' (DIGIT)+;
DIGIT: [0-9];



OPEN_COMMENT : '#*' ;
CLOSE_COMMENT : '*#' ;
COMMENT : OPEN_COMMENT (COMMENT | .)*? CLOSE_COMMENT -> skip ;
ONE_LINE_COMMENT : '#' (~ '\n')* '\n'? -> skip ;

WHITESPACE : [ \t\r\n\f]+ -> skip ;