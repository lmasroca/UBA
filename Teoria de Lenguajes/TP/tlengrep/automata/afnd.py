from enum import Enum
from typing import Hashable, Union

from automata.af import AF
from automata.afd import AFD


__all__ = ["AFND"]


class SpecialSymbol(Enum):
    Lambda = "λ"


class AFND(AF):
    """Autómata finito no determinístico (con transiciones lambda)."""
    def add_transition(self, state1: Hashable, state2: Hashable, char: Union[str, SpecialSymbol]):
        """Agrega una transición al autómata."""
        if state1 not in self.states:
            raise ValueError(f"El estado {state1} no pertenece al autómata.")
        if state2 not in self.states:
            raise ValueError(f"El estado {state2} no pertenece al autómata.")
        if char not in self.transitions[state1]:
            self.transitions[state1][char] = set()
        self.transitions[state1][char].add(state2)
        if char is not SpecialSymbol.Lambda:
            self.alphabet.add(char)

    def determinize(self) -> AFD:
        """Determiniza el autómata."""
        # algoritmo de la clase practica
        def move(T: set[Hashable], a: str) -> set[Hashable]:
            acc = set()
            for t in T:
                if a in self.transitions[t]:
                    acc.update(self.transitions[t][a])
            return acc

        def lambda_closure(T: set[Hashable]) -> set[Hashable]:
            acc = T.copy()
            S = []
            S.extend(T)
            while S:
                q = S.pop()
                if SpecialSymbol.Lambda in self.transitions[q]:
                    S.extend(self.transitions[q][SpecialSymbol.Lambda] - acc)
                    acc.update(self.transitions[q][SpecialSymbol.Lambda])
            return acc

        def lambda_move(T: set[Hashable], a: str) -> set[Hashable]:
            return lambda_closure(move(T, a))

        afd = AFD()
        # frozenset para que sea hasheable el set
        Q0 = frozenset(lambda_closure({self.initial_state}))
        afd.add_state(Q0, not Q0.isdisjoint(frozenset(self.final_states)))
        afd.mark_initial_state(Q0)
        S = [Q0]
        while S:
            t = S.pop(0)
            for a in self.alphabet:
                if a is not SpecialSymbol.Lambda:
                    U = frozenset(lambda_move(t, a))
                    if U not in afd.states:
                        S.append(U)
                        afd.add_state(U, not U.isdisjoint(frozenset(self.final_states)))
                    afd.add_transition(t, U, a)
        afd.alphabet = self.alphabet - {SpecialSymbol.Lambda}
        afd.normalize_states()
        return afd

    def _rename_state_in_transitions(self, old_name: Hashable, new_name: Hashable):
        """Renombra un estado dentro de las transiciones del autómata."""
        self.transitions[new_name] = self.transitions[old_name]
        del self.transitions[old_name]
        for state in self.transitions:
            for char in self.transitions[state]:
                if old_name in self.transitions[state][char]:
                    self.transitions[state][char].remove(old_name)
                    self.transitions[state][char].add(new_name)

    def _get_extended_alphabet(self) -> list[str]:
        """Obtiene el alfabeto extendido del autómata (incluyendo símbolos especiales)."""
        return list(self.alphabet) + [SpecialSymbol.Lambda]

    def _transitions_to_str(self, state: Hashable) -> dict[Hashable, str]:
        """Devuelve las transiciones de un estado para cada símbolo como string."""
        transitions = {}
        for char in self._get_extended_alphabet():
            if char in self.transitions[state]:
                transitions[char] = ",".join(self.transitions[state][char])
            else:
                transitions[char] = "-"
        return transitions
