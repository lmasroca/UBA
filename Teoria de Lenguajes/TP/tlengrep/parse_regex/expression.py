from .errors import SyntaxError

class Expression():
    def evaluate(self):
        raise NotImplementedError

    # Como no podemos aplicarle un cuantificador a un cuantificador (a**), usamos este metodo para verificar esos casos
    def isQuantifier(self):
        return False

class Quantifier(Expression):
    def isQuantifier(self):
        return True

class Union(Expression):
    def __init__(self, op1, op2):
        self.op1 = op1
        self.op2 = op2

    def evaluate(self):
        return 'Union(' + self.op1.evaluate() + ', ' + self.op2.evaluate() + ')'

class Concat(Expression):
    def __init__(self, op1, op2):
        self.op1 = op1
        self.op2 = op2

    def evaluate(self):
        return 'Concat(' + self.op1.evaluate() + ', ' + self.op2.evaluate() + ')'

class Star(Quantifier):
    def __init__(self, op1):
        self.op1 = op1

    def evaluate(self):
        if self.op1.isQuantifier():
            raise SyntaxError
        return 'Star(' + self.op1.evaluate() + ')'

class Plus(Quantifier):
    def __init__(self, op1):
        self.op1 = op1

    def evaluate(self):
        if self.op1.isQuantifier():
            raise SyntaxError
        return 'Plus(' + self.op1.evaluate() + ')'

class Optional(Quantifier):
    def __init__(self, op1):
        self.op1 = op1

    def evaluate(self):
        if self.op1.isQuantifier():
            raise SyntaxError
        return Union(self.op1, Lambda()).evaluate()

class SimpleQuantifier(Quantifier):
    def __init__(self, op1, op2):
        self.op1 = op1
        self.op2 = op2

    def evaluate(self):
        if self.op1.isQuantifier():
            raise SyntaxError
        r1 = self.op1.evaluate()
        if int(self.op2) == 0:
            return Lambda().evaluate()
        res = ''
        for _ in range(int(self.op2) - 1):
            res += 'Concat(' + r1 + ', '
        res += r1 + ')' * (int(self.op2) - 1)
        return res

class DoubleQuantifier(Quantifier):
    def __init__(self, op1, op2, op3):
        self.op1 = op1
        self.op2 = op2
        self.op3 = op3

    def evaluate(self):
        if self.op1.isQuantifier():
            raise SyntaxError
        r_start = int(self.op2)
        r_end = int(self.op3)
        res = ''
        if r_start > r_end:
            raise SyntaxError
        for i in range(r_start, r_end):
            res += 'Union(' + SimpleQuantifier(self.op1, i).evaluate() + ', '
        res += SimpleQuantifier(self.op1, r_end).evaluate() + ')' * (r_end - r_start)
        return res

class CharClass(Expression):
    def __init__(self, op1):
        self.op1 = op1

    def evaluate(self):
        return self.op1.evaluate()

class EmptyCharClass(Expression):
    def evaluate(self):
        return 'Empty()'

class ItemRange(Expression):
    def __init__(self, op1, op2, op3):
        self.op1 = op1
        self.op2 = op2
        self.op3 = op3

    def evaluate(self):
        r_start = ord(self.op1)
        r_end = ord(self.op2)
        res = 'Union('
        if r_start > r_end:
            raise SyntaxError
        for i in range(r_start, r_end):
            res += 'Union(' + Char(chr(i)).evaluate() + ', '
        res += Char(chr(r_end)).evaluate() + ')' * (r_end - r_start) + ', ' + self.op3.evaluate() + ')'
        return res

class Item(Expression):
    def __init__(self, op1, op2):
        self.op1 = op1
        self.op2 = op2

    def evaluate(self):
        return Union(Char(self.op1), self.op2).evaluate()

class SingleItemRange(Expression):
    def __init__(self, op1, op2):
        self.op1 = op1
        self.op2 = op2

    def evaluate(self):
        r_start = ord(self.op1)
        r_end = ord(self.op2)
        res = ''
        if r_start > r_end:
            raise SyntaxError
        for i in range(r_start, r_end):
            res += 'Union(' + Char(chr(i)).evaluate() + ', '
        res += Char(chr(r_end)).evaluate() + ')' * (r_end - r_start)
        return res

class Par(Expression):
    def __init__(self, op1):
        self.op1 = op1

    def evaluate(self):
        return self.op1.evaluate()

class Digit(Expression):
    def evaluate(self):
        return SingleItemRange('0', '9').evaluate()

class Alpha(Expression):
    def evaluate(self):
        return Union(Char('_'), Union(SingleItemRange('a', 'z'), Union(SingleItemRange('A', 'Z'), SingleItemRange('0', '9')))).evaluate()

class Char(Expression):
    def __init__(self, op1):
        self.op1 = op1

    def evaluate(self):
        # si es un caracter escapado (poreque por ejemplo estaba reservado como token) lo "desescapamos", salvo que sea \, si desescapamos eso cuando ponga Char('\') se rompe
        if self.op1 != '\\\\' and self.op1.startswith('\\'):
            return 'Char(\"' + self.op1[1:] + '\")'
        return 'Char(\"' + self.op1 + '\")'

class Lambda(Expression):
    def evaluate(self):
        return 'Lambda()'