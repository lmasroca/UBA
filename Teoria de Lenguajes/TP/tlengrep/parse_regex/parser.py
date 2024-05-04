from .ply.yacc import yacc
from .lexer import tokens
from .expression import *

__all__ = ["parser", "parse_and_print"]

# las palabras en mayusculas son los tokens del lexer, las palabras en minuscula son los no terminales y λ es la cadena vacia
# start -> expression | λ
# expression -> expression UNI concatenation | concatenation
# concatenation -> concatenation CONCAT quantifier | quantifier
# quantifier -> quantifier STAR | quantifier PLUS | quantifier OPT | quantifier SQUANTIFIER | quantifier DQUANTIFIER | constant
# constant -> ALPHA | DIGIT | CHAR | RSV | RANGE | POPEN start PCLOSE | COPEN charclasscontents CCLOSE | COPEN CCLOSE
# charclasscontents -> RANGE charclassitems | charclassitems | λ
# charclassitems -> CHAR RANGE CHAR charclassitems | CHAR charclassitems | RSV charclassitems | CHAR RANGE CHAR | CHAR | RSV | RANGE

def p_start_empty(p):
    '''
    start :
    '''
    p[0] = Lambda()

def p_start_expression(p):
    '''
    start : expression
    '''
    p[0] = p[1]

def p_expression_union(p):
    '''
    expression : expression UNI concatenation
    '''
    p[0] = Union(p[1], p[3])

def p_expression_concatenation(p):
    '''
    expression : concatenation
    '''
    p[0] = p[1]

def p_concatenation_concat(p):
    '''
    concatenation : concatenation quantifier
    '''
    p[0] = Concat(p[1], p[2])

def p_concatenation_quantifier(p):
    '''
    concatenation : quantifier
    '''
    p[0] = p[1]

def p_quantifier_star(p):
    '''
    quantifier : quantifier STAR
    '''
    p[0] = Star(p[1])

def p_quantifier_plus(p):
    '''
    quantifier : quantifier PLUS
    '''
    p[0] = Plus(p[1])

def p_quantifier_optional(p):
    '''
    quantifier : quantifier OPT
    '''
    p[0] = Optional(p[1])

def p_quantifier_simplequantifier(p):
    '''
    quantifier : quantifier SQUANTIFIER
    '''
    q = p[2][1:-1]
    p[0] = SimpleQuantifier(p[1], q)

def p_quantifier_doublequantifier(p):
    '''
    quantifier : quantifier DQUANTIFIER
    '''
    q = p[2][1:-1].split(',')
    p[0] = DoubleQuantifier(p[1], q[0], q[1])

def p_quantifier_constant(p):
    '''
    quantifier : constant
    '''
    p[0] = p[1]

def p_constant_par(p):
    '''
    constant : POPEN start PCLOSE
    '''
    p[0] = Par(p[2])

def p_constant_digit(p):
    '''
    constant : DIGIT
    '''
    p[0] = Digit()

def p_constant_alpha(p):
    '''
    constant : ALPHA
    '''
    p[0] = Alpha()

def p_constant_char(p):
    '''
    constant : CHAR
    '''
    p[0] = Char(p[1])

def p_constant_range(p):
    '''
    constant : RANGE
    '''
    p[0] = Char(p[1])

def p_constant_rsv(p):
    '''
    constant : RSV
    '''
    p[0] = Char(p[1])

def p_constant_charclasscontents(p):
    '''
    constant : COPEN charclasscontents CCLOSE
    '''
    p[0] = CharClass(p[2])

def p_constant_emptycharclass(p):
    '''
    constant : COPEN CCLOSE
    '''
    p[0] = EmptyCharClass()

def p_charclasscontents_rangechar(p):
    '''
    charclasscontents : RANGE charclassitems
    '''
    p[0] = Item(p[1], p[2])

def p_charclasscontents_charclassitems(p):
    '''
    charclasscontents : charclassitems
    '''
    p[0] = p[1]

def p_charclassitems_itemrange(p):
    '''
    charclassitems : CHAR RANGE CHAR charclassitems
    '''
    p[0] = ItemRange(p[1], p[3], p[4])

def p_charclassitems_singleitemrange(p):
    '''
    charclassitems : CHAR RANGE CHAR
    '''
    p[0] = SingleItemRange(p[1], p[3])

def p_charclassitems_item(p):
    '''
    charclassitems : CHAR charclassitems
    '''
    p[0] = Item(p[1], p[2])

def p_charclassitems_singleitem(p):
    '''
    charclassitems : CHAR
    '''
    p[0] = Char(p[1])

def p_charclassitems_rsvitem(p):
    '''
    charclassitems : RSV charclassitems
    '''
    p[0] = Item(p[1], p[2])

def p_charclassitems_singlersvitem(p):
    '''
    charclassitems : RSV
    '''
    p[0] = Char(p[1])

def p_charclassitems_singlerangeitem(p):
    '''
    charclassitems : RANGE
    '''
    p[0] = Char(p[1])

# Manejo de errores
def p_error(p):
    raise SyntaxError

class ParseError(Exception):
    pass

parser = yacc(debug=True)

def parse_and_print(regex_str):
    parse_result = parser.parse(regex_str)
    return parse_result.evaluate()