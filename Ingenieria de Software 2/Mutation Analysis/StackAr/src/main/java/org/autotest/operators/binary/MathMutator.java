package org.autotest.operators.binary;

import org.autotest.helpers.BinaryOperatorKindToString;
import org.autotest.operators.MutationOperator;
import spoon.reflect.code.BinaryOperatorKind;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.declaration.CtElement;

import java.util.Arrays;
import java.util.List;

import static spoon.reflect.code.BinaryOperatorKind.*;

/**
 * Operador de mutación basado en https://pitest.org/quickstart/mutators/#MATH
 *
 * Este operador de mutación reemplaza las operaciones aritméticas binarias para enteros o punto flotante con otra operación.
 */
public class MathMutator extends MutationOperator {
    @Override
    public boolean isToBeProcessed(CtElement candidate) {
        if (!(candidate instanceof CtBinaryOperator)) {
            return false;
        }

        CtBinaryOperator op = (CtBinaryOperator)candidate;
        List<BinaryOperatorKind> targetOperations = Arrays.asList(
                MINUS, // -
                PLUS, // +
                MUL, // *
                MOD, // %
                DIV, // /
                AND, // &
                OR, // |
                SL, // <<
                SR, // >>
                USR // >>>


        );
        return targetOperations.contains(op.getKind());
    }

    @Override
    public void process(CtElement candidate) {
        CtBinaryOperator op = (CtBinaryOperator)candidate;
        op.setKind(getReplacement(op.getKind()));
    }

    private BinaryOperatorKind getReplacement(BinaryOperatorKind kind) {
        switch (kind){
            case OR:
                return AND;
            case AND:
                return OR;
            case SL:
                return SR;
            case SR:
                return SL;
            case USR:
                return SL;
            case PLUS:
                return MINUS;
            case MINUS:
                return PLUS;
            case MUL:
                return DIV;
            case DIV:
                return MUL;
            case MOD:
                return MUL;
        }
        return null;
    }

    @Override
    public String describeMutation(CtElement candidate) {
        CtBinaryOperator op = (CtBinaryOperator)candidate;
        return this.getClass().getSimpleName() + ": Se reemplazó " +
                BinaryOperatorKindToString.get(op.getKind()) + " por " + BinaryOperatorKindToString.get(getReplacement(op.getKind())) +
                " en la línea " + op.getPosition().getLine() + ".";
    }
}
