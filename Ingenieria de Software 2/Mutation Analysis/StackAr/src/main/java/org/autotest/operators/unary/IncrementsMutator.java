package org.autotest.operators.unary;

import org.autotest.helpers.BinaryOperatorKindToString;
import org.autotest.helpers.UnaryOperatorKindToString;
import org.autotest.operators.MutationOperator;
import spoon.reflect.code.CtBinaryOperator;
import spoon.reflect.code.CtUnaryOperator;
import spoon.reflect.code.UnaryOperatorKind;
import spoon.reflect.declaration.CtElement;

import java.util.Arrays;
import java.util.List;
import java.util.function.UnaryOperator;

import static spoon.reflect.code.UnaryOperatorKind.*;

/**
 * Operador de mutación basado en https://pitest.org/quickstart/mutators/#INCREMENTS
 *
 * Este operador de mutación reemplaza los operadores de incremento y decremento de variables locales (variables de pila).
 */
public class IncrementsMutator extends MutationOperator {
    @Override
    public boolean isToBeProcessed(CtElement candidate) {
        if (!(candidate instanceof CtUnaryOperator)) {
            return false;
        }
        CtUnaryOperator op = (CtUnaryOperator) candidate;
        List<UnaryOperatorKind> targetOperations = Arrays.asList(
                POSTDEC, // i--
                POSTINC, // i++
                PREDEC, // --i
                PREINC // ++i
        );
        return targetOperations.contains(op.getKind());
    }

    @Override
    public void process(CtElement candidate) {
        CtUnaryOperator op = (CtUnaryOperator) candidate;
        op.setKind(getReplacement(op.getKind()));
    }

    private UnaryOperatorKind getReplacement(UnaryOperatorKind kind) {
        switch (kind) {
            case PREINC:
                return PREDEC;
            case PREDEC:
                return PREINC;
            case POSTINC:
                return POSTDEC;
            case POSTDEC:
                return POSTINC;
        }
        return null;
    }

    @Override
    public String describeMutation(CtElement candidate) {
        CtUnaryOperator op = (CtUnaryOperator)candidate;
        return this.getClass().getSimpleName() + ": Se reemplazó " +
                UnaryOperatorKindToString.get(op.getKind()) + " por " + UnaryOperatorKindToString.get(getReplacement(op.getKind())) +
                " en la línea " + op.getPosition().getLine() + ".";
    }
}
