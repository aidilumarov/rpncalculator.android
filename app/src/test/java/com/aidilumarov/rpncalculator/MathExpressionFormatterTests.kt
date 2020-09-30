package com.aidilumarov.rpncalculator

import org.junit.Test
import com.aidilumarov.rpncalculator.ModelLayer.core.MathExpression
import com.aidilumarov.rpncalculator.ModelLayer.core.MathExpressionFormatter

import org.junit.Assert.*

class MathExpressionFormatterTests {

    @Test
    fun formatExpression_isCorrect() {

        var formatter =
            MathExpressionFormatter()
        var mathExpression =
            MathExpression()
        mathExpression.expression = "(-5.0)*5"
        var actual = formatter.formatExpression(mathExpression)

        assertEquals("-5.0 * 5", actual)
    }
}