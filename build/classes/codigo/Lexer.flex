package codigo;
import static codigo.Tokens.*;
%%
%class Lexer
%type Tokens
%line
%column
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ \t\r\n]+
%{
    public String lexeme;
%}
%%

{espacio} {/* ignorar espacios */}

/* Comentarios */
( "//"(.)* ) {/* ignorar */}
( "/*" ~"*/" ) {/* ignorar comentarios multilínea */}

/* Include */
"#include" { lexeme = yytext(); return Include; }

/* Librerías tipo <stdio.h>, <stdlib.h> */
"<"{L}({L}|{D})*(".h")">" { lexeme=yytext(); return Libreria; }

/* Librerías tipo "mylib.h" */
"\""{L}({L}|{D})*(".h")"\"" { lexeme=yytext(); return Libreria; }

/* Cadenas de texto completas entre comillas */
"\"" [^\"\\]*(\\.[^\"\\]*)* "\"" { lexeme=yytext(); return Cadena_texto; }

/* Comillas individuales para gramática */
\" { lexeme=yytext(); return Comillas; }

/* Tipos de datos */
(byte|int|char|long|float|double) { lexeme=yytext(); return TIPO_DATO; }
String { lexeme=yytext(); return Cadena; }

/* Palabras reservadas */
if { lexeme=yytext(); return If; }
else { lexeme=yytext(); return Else; }
do { lexeme=yytext(); return Do; }
while { lexeme=yytext(); return While; }
for { lexeme=yytext(); return For; }
main { lexeme=yytext(); return MAIN_T; }
void { lexeme=yytext(); return Void; }
class { lexeme=yytext(); return CLASS_T; }
print { lexeme=yytext(); return Print; }
printf { lexeme=yytext(); return Printf; }
scanf { lexeme=yytext(); return Scanf; }
return { lexeme=yytext(); return Return; }

/* Operadores aritméticos y asignación */
"=" { lexeme=yytext(); return Igual; }
"+" { lexeme=yytext(); return Suma; }
"-" { lexeme=yytext(); return Resta; }
"*" { lexeme=yytext(); return Multiplicacion; }
"/" { lexeme=yytext(); return Division; }
"%" { lexeme=yytext(); return Modulo; }

/* Operadores lógicos */
"&&"|"||"|"!"|"&"|"|" { lexeme=yytext(); return Op_logico; }

/* Operadores relacionales */
">"|"<"|"=="|"!="|">="|"<=" { lexeme=yytext(); return Op_relacional; }

/* Operadores de asignación y incremento */
"+="|"-="|"*="|"/="|"%="|"&="|"|="|"^="|"<<="|">>=" { lexeme=yytext(); return Op_atribucion; }
"++"|"--" { lexeme=yytext(); return Op_incremento; }

/* Booleanos */
true|false { lexeme=yytext(); return Op_booleano; }

/* Paréntesis, llaves y corchetes */
"(" { lexeme=yytext(); return Parentesis_a; }
")" { lexeme=yytext(); return Parentesis_c; }
"{" { lexeme=yytext(); return Llave_a; }
"}" { lexeme=yytext(); return Llave_c; }
"[" { lexeme=yytext(); return Corchete_a; }
"]" { lexeme=yytext(); return Corchete_c; }

/* Otros símbolos */
"," { lexeme=yytext(); return Coma; }
";" { lexeme=yytext(); return P_coma; }
":" { lexeme=yytext(); return DosPuntos; }
"?" { lexeme=yytext(); return Interrogacion; }
"!" { lexeme=yytext(); return Exclamacion; }

/* Directivas de preprocesador */
("#define"|"#ifdef"|"#ifndef"|"#endif"|"#else"|"#elif") { lexeme=yytext(); return Directiva; }

/* Números flotantes y enteros - ORDEN IMPORTANTE */
{D}+\.{D}*([eE][-+]?{D}+)?   { lexeme = yytext(); return Numero_decimal; }  /* flotante PRIMERO */
{D}+                          { lexeme = yytext(); return Numero; }          /* entero DESPUÉS */

/* Identificadores */
{L}({L}|{D})* { lexeme=yytext(); return Identificador; }

/* Cualquier otro carácter */
. { lexeme=yytext(); return ERROR; }