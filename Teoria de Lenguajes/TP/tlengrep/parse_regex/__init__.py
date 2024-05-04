import sys
from .ply.yacc import yacc
from .lexer import tokens, tokenize_and_print
from .expression import *
from regex import RegEx, Char, Union, Concat, Star, Plus, Lambda, Empty
from .errors import SyntaxError
from .parser import parse_and_print, ParseError


__all__ = ["parse_regex", "SyntaxError"]

def parse_regex(regex_str: str) -> RegEx:
    # NO IMPLEMENTAR este método para la primera entrega del TP.
    # Forma parte de la segunda entrega.
    return eval(parse_and_print(regex_str))
