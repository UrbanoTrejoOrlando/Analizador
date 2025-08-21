package codigo;
import java_cup.runtime.Symbol;

%%
%class LexerCup
%function next_token
%type java_cup.runtime.Symbol
%cup
%line
%char
%unicode

L = [a-zA-Z_]+
D = [0-9]+
espacio = [ \t\r\n]+

%{
public String lexeme;
%}

%%

{espacio} { /* ignorar espacios */ }

/* Comentarios */
"//".* { /* ignorar */ }
"/*" ~"*/" { /* ignorar comentarios multilínea */ }

"#include" { return new Symbol(sym.Include, yychar, yyline, yytext()); }

/* Librerías tipo <stdio.h>, <stdlib.h> */
"<"{L}({L}|{D})*(".h")">" { return new Symbol(sym.Libreria, yychar, yyline, yytext()); }

/* Librerías tipo "mylib.h" */
"\""{L}({L}|{D})*(".h")"\"" { return new Symbol(sym.Libreria, yychar, yyline, yytext()); }

/* Cadenas de texto completas entre comillas */
"\"" [^\"\\]*(\\.[^\"\\]*)* "\"" { return new Symbol(sym.Cadena_texto, yychar, yyline, yytext()); }

/* Comillas individuales */
\" { return new Symbol(sym.Comillas, yychar, yyline, yytext()); }

(byte|int|char|long|float|double) { return new Symbol(sym.TIPO_DATO, yychar, yyline, yytext()); }
String { return new Symbol(sym.Cadena, yychar, yyline, yytext()); }

if { return new Symbol(sym.If, yychar, yyline, yytext()); }
else { return new Symbol(sym.Else, yychar, yyline, yytext()); }
do { return new Symbol(sym.Do, yychar, yyline, yytext()); }
while { return new Symbol(sym.While, yychar, yyline, yytext()); }
for { return new Symbol(sym.For, yychar, yyline, yytext()); }
void { return new Symbol(sym.Void, yychar, yyline, yytext()); }
main { return new Symbol(sym.MAIN_T, yychar, yyline, yytext()); }
class { return new Symbol(sym.CLASS_T, yychar, yyline, yytext()); }
print { return new Symbol(sym.Print, yychar, yyline, yytext()); }
printf { return new Symbol(sym.Printf, yychar, yyline, yytext()); }
scanf { return new Symbol(sym.Scanf, yychar, yyline, yytext()); }
return { return new Symbol(sym.Return, yychar, yyline, yytext()); }

"=" { return new Symbol(sym.Igual, yychar, yyline, yytext()); }
"+" { return new Symbol(sym.Suma, yychar, yyline, yytext()); }
"-" { return new Symbol(sym.Resta, yychar, yyline, yytext()); }
"*" { return new Symbol(sym.Multiplicacion, yychar, yyline, yytext()); }
"/" { return new Symbol(sym.Division, yychar, yyline, yytext()); }
"%" { return new Symbol(sym.Modulo, yychar, yyline, yytext()); }

"&&"|"||"|"!"|"&"|"|" { return new Symbol(sym.Op_logico, yychar, yyline, yytext()); }
">"|"<"|"=="|"!="|">="|"<=" { return new Symbol(sym.Op_relacional, yychar, yyline, yytext()); }

"+="|"-="|"*="|"/="|"%="|"&="|"|="|"^="|"<<="|">>=" { return new Symbol(sym.Op_atribucion, yychar, yyline, yytext()); }
"++"|"--" { return new Symbol(sym.Op_incremento, yychar, yyline, yytext()); }
true|false { return new Symbol(sym.Op_booleano, yychar, yyline, yytext()); }

"(" { return new Symbol(sym.Parentesis_a, yychar, yyline, yytext()); }
")" { return new Symbol(sym.Parentesis_c, yychar, yyline, yytext()); }

"{" { return new Symbol(sym.Llave_a, yychar, yyline, yytext()); }
"}" { return new Symbol(sym.Llave_c, yychar, yyline, yytext()); }

"[" { return new Symbol(sym.Corchete_a, yychar, yyline, yytext()); }
"]" { return new Symbol(sym.Corchete_c, yychar, yyline, yytext()); }

"," { return new Symbol(sym.Coma, yychar, yyline, yytext()); }
";" { return new Symbol(sym.P_coma, yychar, yyline, yytext()); }
":" { return new Symbol(sym.DosPuntos, yychar, yyline, yytext()); }
"?" { return new Symbol(sym.Interrogacion, yychar, yyline, yytext()); }
"!" { return new Symbol(sym.Exclamacion, yychar, yyline, yytext()); }

/* Directivas de preprocesador */
("#define"|"#ifdef"|"#ifndef"|"#endif"|"#else"|"#elif") { return new Symbol(sym.Directiva, yychar, yyline, yytext()); }

/* Números - ORDEN IMPORTANTE: decimales primero, enteros después */
{D}+\.{D}*([eE][-+]?{D}+)? { return new Symbol(sym.Numero_decimal, yychar, yyline, yytext()); }
{D}+ { return new Symbol(sym.Numero, yychar, yyline, yytext()); }

{L}({L}|{D})* { return new Symbol(sym.Identificador, yychar, yyline, yytext()); }

\n { return new Symbol(sym.Linea, yychar, yyline, yytext()); }
. { return new Symbol(sym.ERROR, yychar, yyline, yytext()); }