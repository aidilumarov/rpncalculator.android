package com.aidilumarov.rpncalculator

import org.junit.Test
import com.aidilumarov.rpncalculator.ModelLayer.*

import org.junit.Assert.*
import org.junit.experimental.theories.ParametersSuppliedBy
import org.junit.runners.Parameterized

import java.util.stream.Stream

class MathExpressionFormatterTests {

    @Test
    fun formatExpression_isCorrect() {

        var formatter = MathExpressionFormatter()
        var mathExpression = MathExpression()
        mathExpression.expression = "(-5.0)*5"
        var actual = formatter.formatExpression(mathExpression)

        assertEquals("-5.0 * 5", actual)
    }
}