from abc import ABC, abstractmethod

from automata import AFND
from automata.afnd import SpecialSymbol

__all__ = [
    "RegEx",
    "Empty",
    "Lambda",
    "Char",
    "Union",
    "Concat",
    "Star",
    "Plus"
]


class RegEx(ABC):
    """Clase abstracta para representar expresiones regulares."""
    def __init__(self):
        self.afdm = None

    @abstractmethod
    def naive_match(self, word: str) -> bool:
        """
        Indica si la expresión regular acepta la cadena dada.
        Implementación recursiva, poco eficiente.
        """
        pass

    def match(self, word: str) -> bool:
        """Indica si la expresión regular acepta la cadena dada."""
        # esto hay que moverlo a otro lado asi no generamos el AFND, det y min cada vez que queremos matchear algo
        # por eso lo meti en el init como None y aca lo genero si todavia no se genero
        # ----------------------------------
        if self.afdm is None:
            self.afdm = self.to_afnd().determinize().minimize()
        afdm = self.afdm
        # ----------------------------------
        q = afdm.initial_state
        for symbol in word:
            if symbol not in afdm.transitions[q]:
                return False
            q = afdm.transitions[q][symbol]
        return q in afdm.final_states

    @abstractmethod
    def to_afnd(self) -> AFND:
        """Convierte la expresión regular a un AFND."""
        pass

    @abstractmethod
    def _atomic(self) -> bool:
        """
        (Interno) Indica si la expresión regular es atómica. Útil para
        implementar la función __str__.
        """
        pass


class Empty(RegEx):
    """Expresión regular que denota el lenguaje vacío (∅)."""

    def naive_match(self, word: str):
        return False

    def to_afnd(self) -> AFND:
        a = AFND()
        a.add_state(0, False)
        a.mark_initial_state(0)
        a.normalize_states()
        return a

    def _atomic(self):
        return True

    def __str__(self):
        return "∅"


class Lambda(RegEx):
    """Expresión regular que denota el lenguaje de la cadena vacía (Λ)."""

    def naive_match(self, word: str):
        return word == ""

    def to_afnd(self) -> AFND:
        a = AFND()
        a.add_state(0, True)
        a.mark_initial_state(0)
        a.normalize_states()
        return a

    def _atomic(self):
        return True

    def __str__(self):
        return "λ"


class Char(RegEx):
    """Expresión regular que denota el lenguaje de un determinado carácter."""

    def __init__(self, char: str):
        assert len(char) == 1
        self.char = char
        self.afdm = None

    def naive_match(self, word: str):
        return word == self.char

    def to_afnd(self) -> AFND:
        a = AFND()
        a.add_state(0, False)
        a.add_state(1, True)
        a.mark_initial_state(0)
        a.add_transition(0, 1, self.char)
        a.alphabet.add(self.char)
        a.normalize_states()
        return a

    def _atomic(self):
        return True

    def __str__(self):
        return self.char


class Concat(RegEx):
    """Expresión regular que denota la concatenación de dos expresiones regulares."""

    def __init__(self, exp1: RegEx, exp2: RegEx):
        self.exp1 = exp1
        self.exp2 = exp2
        self.afdm = None

    def naive_match(self, word: str):
        for i in range(len(word) + 1):
            if self.exp1.naive_match(word[:i]) and self.exp2.naive_match(word[i:]):
                return True
        return False

    def to_afnd(self) -> AFND:
        a = self.exp1.to_afnd()
        b = self.exp2.to_afnd()
        c = AFND()
        for state in a.states:
            c.add_state(state, False)
        c.transitions = a.transitions
        for state in b.states:
            c.add_state("q_" + state[1:], state in b.final_states)
        for state in b.states:
            for symbol in b.alphabet:
                if symbol in b.transitions[state]:
                    for q in b.transitions[state][symbol]:
                        c.add_transition("q_" + state[1:], "q_" + q[1:], symbol)
        c.mark_initial_state(a.initial_state)
        for final_state in a.final_states:
            c.add_transition(final_state, "q_" + b.initial_state[1:], SpecialSymbol.Lambda)
        c.alphabet.add(SpecialSymbol.Lambda)
        c.alphabet.update(a.alphabet)
        c.alphabet.update(b.alphabet)
        c.normalize_states()
        return c

    def _atomic(self):
        return False

    def __str__(self):
        return f"{f'({self.exp1})' if not self.exp1._atomic() else self.exp1}" \
            f"{f'({self.exp2})' if not self.exp2._atomic() else self.exp2}"


class Union(RegEx):
    """Expresión regular que denota la unión de dos expresiones regulares."""

    def __init__(self, exp1: RegEx, exp2: RegEx):
        self.exp1 = exp1
        self.exp2 = exp2
        self.afdm = None

    def naive_match(self, word: str):
        return self.exp1.naive_match(word) or self.exp2.naive_match(word)

    def to_afnd(self) -> AFND:
        a = self.exp1.to_afnd()
        b = self.exp2.to_afnd()
        c = AFND()
        for state in a.states:
            c.add_state(state, state in a.final_states)
        c.transitions = a.transitions
        for state in b.states:
            c.add_state("q_" + state[1:], state in b.final_states)
        for state in b.states:
            for symbol in b.alphabet:
                if symbol in b.transitions[state]:
                    for q in b.transitions[state][symbol]:
                        c.add_transition("q_" + state[1:], "q_" + q[1:], symbol)
        c.add_state("Q_i")
        c.mark_initial_state("Q_i")
        c.add_transition("Q_i", a.initial_state, SpecialSymbol.Lambda)
        c.add_transition("Q_i", "q_" + b.initial_state[1:], SpecialSymbol.Lambda)
        c.alphabet.add(SpecialSymbol.Lambda)
        c.alphabet.update(a.alphabet)
        c.alphabet.update(b.alphabet)
        c.normalize_states()
        return c

    def _atomic(self):
        return False

    def __str__(self):
        return f"{f'({self.exp1})' if not self.exp1._atomic() else self.exp1}" \
            f"|{f'({self.exp2})' if not self.exp2._atomic() else self.exp2}"


class Star(RegEx):
    """Expresión regular que denota la clausura de Kleene de otra expresión regular."""

    def __init__(self, exp: RegEx):
        self.exp = exp
        self.afdm = None

    def naive_match(self, word: str):
        if word == "" or self.exp.naive_match(word):
            return True
        for i in range(1, len(word) + 1):
            if self.exp.naive_match(word[:i]) and self.naive_match(word[i:]):
                return True
        return False

    def to_afnd(self) -> AFND:
        a = self.exp.to_afnd()
        a.add_state("Q_i")
        a.mark_initial_state("Q_i")
        for final_state in a.final_states:
            a.add_transition("Q_i", "q0", SpecialSymbol.Lambda)
            a.add_transition(final_state, "Q_i", SpecialSymbol.Lambda)
            a.add_transition("Q_i", final_state, SpecialSymbol.Lambda)
        a.alphabet.add(SpecialSymbol.Lambda)
        a.normalize_states()
        return a

    def _atomic(self):
        return False

    def __str__(self):
        return f"({self.exp})*" if not self.exp._atomic() else f"{self.exp}*"


class Plus(RegEx):
    """Expresión regular que denota la clausura positiva de otra expresión regular."""

    def __init__(self, exp: RegEx):
        self.exp = exp
        self.afdm = None

    def naive_match(self, word: str):
        if self.exp.naive_match(word):
            return True
        for i in range(1, len(word) + 1):
            if self.exp.naive_match(word[:i]) and self.naive_match(word[i:]):
                return True
        return False

    def to_afnd(self) -> AFND:
        a = self.exp.to_afnd()
        a.add_state("Q_i")
        a.mark_initial_state("Q_i")
        for final_state in a.final_states:
            a.add_transition("Q_i", "q0", SpecialSymbol.Lambda)
            a.add_transition(final_state, "Q_i", SpecialSymbol.Lambda)
        a.alphabet.add(SpecialSymbol.Lambda)
        a.normalize_states()
        return a

    def _atomic(self) -> bool:
        return False

    def __str__(self):
        return f"({self.exp})+" if not self.exp._atomic() else f"{self.exp}+"
