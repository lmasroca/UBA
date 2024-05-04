from .ply.lex import lex
import sys

__all__ = ["lexer", "tokens", "tokenize"]

# Lista de tokens reconocibles por el lexer
tokens = ['SQUANTIFIER', 'DQUANTIFIER', 'PLUS', 'STAR', 'OPT', 'UNI', 'DIGIT', 'ALPHA', 'POPEN', 'PCLOSE', 'COPEN', 'CCLOSE', 'RANGE', 'CHAR', 'RSV']

# Regexes para reconocer tokens simples
t_SQUANTIFIER = r'{[0-9]+}'
t_DQUANTIFIER = r'{[0-9]+,[0-9]+}'
t_PLUS = r'\+'
t_STAR = r'\*'
t_OPT = r'\?'
t_UNI = r'\|'
t_DIGIT = r'\\d'
t_ALPHA = r'\\w'
t_POPEN = r'\('
t_PCLOSE = r'\)'
t_COPEN = r'\['
t_CCLOSE = r'\]'
t_RANGE = r'-'
t_CHAR = r'[a-zA-Z0-9_ ,]|\\\+|\\\*|\\\?|\\\||\\\\|\\\(|\\\)|\\\[|\\\]|\\{|\\}'
t_RSV = r'{|}'

def t_error(t):
    raise SyntaxError(f'Illegal character {t.value[0]!r} at position {t.lexpos}')

# Construimos el lexer
lexer = lex()

# Aplicamos el lexer e imprimimos una lista de tokens
def tokenize_and_print(string):
    lexer.input(string)
    print(list(map(lambda token: (token.type, token.value), lexer)))
