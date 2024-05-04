from typing import Hashable
from automata.af import AF

__all__ = ["AFD"]


class AFD(AF):
    """Autómata finito determinístico."""

    def add_transition(self, state1: Hashable, state2: Hashable, char: str):
        """Agrega una transición al autómata."""
        if state1 not in self.states:
            raise ValueError(f"El estado {state1} no pertenece al autómata.")
        if state2 not in self.states:
            raise ValueError(f"El estado {state2} no pertenece al autómata.")
        self.transitions[state1][char] = state2
        self.alphabet.add(char)

    def minimize(self):
        """Minimiza el autómata."""
        # algoritmo de la clase practica (tabla de particiones por clases de equivalencias)
        def initial_partitions(ff, fn):
            '''Armamos las particiones iniciales tomando el primer estado final y el primer estado no final como representantes de sus clases de equivalencia'''
            return {q: (fn if q in self.final_states else ff) if self.initial_state in self.final_states else (ff if q in self.final_states else fn) for q in self.states}

        def build_table_from_partitions(partitions):
            # uso frozenset({...}.items()) para hacer un diccionario hashable inmutable, luego para usarlo como diccionario normal uso dict de el inmutable
            return {q: frozenset({a: partitions[self.transitions[q][a]] for a in self.alphabet}.items()) for q in self.states}

        def calculate_partition_slicing_for_current_partition(Q, partitions, table):
            '''Consideramos un "slicing" una separacion de los estados en las distintas particiones usando:
                - Como clave la tupla: (representante de la particion, transiciones desde esa particion a los representantes de las otras particiones)
                esto en el algoritmo serian los distintos renglones unicos de la tabla en esa iteracion
                - Como valor los estados originales que pertenecen a esa particion'''
            partition_slicing = {}
            for q in Q:
                if (partitions[q], table[q]) not in partition_slicing:
                    partition_slicing[(partitions[q], table[q])] = []
                partition_slicing[(partitions[q], table[q])].append(q)
            return partition_slicing

        def update_partitions_using_slicing(partitions, partition_slicing):
            '''Actualizamos las particiones de los estados definiendo el representante de cada particion nueva'''
            for estados in partition_slicing.values():
                for estado in estados:
                    partitions[estado] = int(estados[0][1:])

        def recalculate_partitions_and_slicing(Q, partitions, table):
            last_partition_slicing = calculate_partition_slicing_for_current_partition(Q, partitions, table)
            update_partitions_using_slicing(partitions, last_partition_slicing)
            return calculate_partition_slicing_for_current_partition(Q, partitions, table) # devuelvo el nuevo slicing

        def build_automata(partitions, partition_slicing):
            '''Construimos el automata minimizado usando las particiones y el slicing calculados'''
            afdm = AFD()
            # lo hago set para no agregar 2 veces el mismo estado
            for partition in set(partitions.values()):
                afdm.add_state(partition, 'q' + str(partition) in self.final_states) # alcanza con checkear uno para ver si es final, en particular miro el representante
            afdm.mark_initial_state(partitions[self.initial_state])
            for (new_state, new_transitions) in partition_slicing.keys():
                for symbol in self.alphabet:
                    afdm.add_transition(new_state, dict(new_transitions)[symbol], symbol)
            afdm.alphabet = self.alphabet
            afdm.normalize_states()
            return afdm

        Q = list(self.states)
        Q.sort()
        ff = int(min(list(self.final_states))[1:]) if self.final_states else -1
        fn = int(min(list(self.states - self.final_states))[1:]) if self.final_states != self.states else -1
        if ff == -1 or fn == -1:
            afdm = AFD()
            afdm.add_state(0, ff != -1)
            afdm.mark_initial_state(0)
            for symbol in self.alphabet:
                afdm.add_transition(0, 0, symbol)
            afdm.normalize_states()
            return afdm
        partitions = initial_partitions(ff, fn)
        last_partitions = None
        while partitions != last_partitions:
            last_partitions = partitions.copy()
            table = build_table_from_partitions(last_partitions)
            partition_slicing = recalculate_partitions_and_slicing(Q, partitions, table)
        return build_automata(partitions, partition_slicing)

    def _rename_state_in_transitions(self, old_name: Hashable, new_name: Hashable):
        """Renombra un estado dentro de las transiciones del autómata."""
        self.transitions[new_name] = self.transitions[old_name]
        del self.transitions[old_name]
        for state in self.transitions:
            for char in self.transitions[state]:
                if self.transitions[state][char] == old_name:
                    self.transitions[state][char] = new_name

    def _get_extended_alphabet(self) -> list[str]:
        """Obtiene el alfabeto extendido del autómata (incluyendo símbolos especiales)."""
        return list(self.alphabet)

    def _transitions_to_str(self, state: Hashable) -> dict[Hashable, str]:
        """Devuelve las transiciones de un estado para cada símbolo como string."""
        transitions = {}
        for char in self._get_extended_alphabet():
            if char in self.transitions[state]:
                transitions[char] = self.transitions[state][char]
            else:
                transitions[char] = "-"
        return transitions
