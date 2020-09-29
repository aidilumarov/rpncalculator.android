package com.aidilumarov.rpncalculator.ModelLayer

import android.view.View
import android.widget.TextView
import com.aidilumarov.rpncalculator.ViewLayer.CalculatorViewActivity

public class InputProcessor {

    // Reference to view
    private val calculatorView: CalculatorViewActivity

    private var mathExpression: MathExpression
    private val formatter: MathExpressionFormatter
    private val calculator: RPNCalculator

    public constructor(calculatorView: CalculatorViewActivity) {
        this.calculatorView = calculatorView
        mathExpression = MathExpression()
        formatter = MathExpressionFormatter()
        calculator = RPNCalculator()
    }

    public fun clear() {
        clear(mathExpression)
    }

    public fun getUpdatedOutputBasedOnClick(clickedView: TextView) : MathExpression {
        when (clickedView) {

            // If brackets were clicked
            calculatorView.buttonCloseBrackets -> processBrackets(clickedView, mathExpression)
            calculatorView.buttonOpenBrackets -> processBrackets(clickedView, mathExpression)

            // If clear was clicked
            calculatorView.buttonClearScreen -> clear(mathExpression)

            // If numbers were clicked
            calculatorView.buttonZero -> processNumbers(clickedView, mathExpression)
            calculatorView.buttonOne -> processNumbers(clickedView, mathExpression)
            calculatorView.buttonTwo -> processNumbers(clickedView, mathExpression)
            calculatorView.buttonThree -> processNumbers(clickedView, mathExpression)
            calculatorView.buttonFour -> processNumbers(clickedView, mathExpression)
            calculatorView.buttonFive -> processNumbers(clickedView, mathExpression)
            calculatorView.buttonSix -> processNumbers(clickedView, mathExpression)
            calculatorView.buttonSeven -> processNumbers(clickedView, mathExpression)
            calculatorView.buttonEight -> processNumbers(clickedView, mathExpression)
            calculatorView.buttonNine -> processNumbers(clickedView, mathExpression)

            // If point was clicked
            calculatorView.buttonPoint -> processPoint(clickedView, mathExpression)

            // If an operator was clicked
            calculatorView.buttonPlus -> processOperators(clickedView, mathExpression)
            calculatorView.buttonMinus -> processOperators(clickedView, mathExpression)
            calculatorView.buttonMultiply -> processOperators(clickedView, mathExpression)
            calculatorView.buttonDivide -> processOperators(clickedView, mathExpression)
            calculatorView.buttonPower -> processOperators(clickedView, mathExpression)

            // If = was clicked
            calculatorView.buttonCalculate -> calculate(mathExpression)
        }

        return mathExpression
    }

    private fun processBrackets(clickedView: TextView, currentExpressionState: MathExpression) {

        if (clickedView == calculatorView.buttonCloseBrackets) {

            // If closing bracket was clicked without opening a bracket
            // or closing bracket was clicked immediately after opening, return
            if (!currentExpressionState.openBracketWasTyped
                || currentExpressionState.expression.last().toString() == calculatorView.buttonOpenBrackets.text
            ) {
                return
            }
        } else {
            if (!currentExpressionState.openBracketWasTyped) {
                currentExpressionState.indexOfFirstBracket = currentExpressionState.expression.length
                currentExpressionState.openBracketWasTyped = true
            }
        }

        currentExpressionState.expression += clickedView.text
    }

    private fun processNumbers(clickedView: TextView, currentExpressionState: MathExpression) {
        currentExpressionState.expression += clickedView.text
        currentExpressionState.operatorIsInsertable = true
    }

    private fun processPoint(clickedView: TextView, currentExpressionState: MathExpression) {

        // If last character in the expression is not digit, then 0 should be prefixed
        if (currentExpressionState.expressionIsEmpty
            || !currentExpressionState.expression.last().isDigit()) {
            currentExpressionState.expression += "0" + clickedView.text
            currentExpressionState.pointIsInsertable = false
        } else {
            if (currentExpressionState.pointIsInsertable) {
                currentExpressionState.expression += clickedView.text
                currentExpressionState.pointIsInsertable = false
            }
        }
    }

    private fun processOperators(clickedView: TextView, currentExpressionState: MathExpression) {
        // If - is clicked, we must check whether or not brackets were opened
        if (clickedView == calculatorView.buttonMinus) {
            if (currentExpressionState.openBracketWasTyped) {
                currentExpressionState.expression += clickedView.text
                return
            }
        }

        if (currentExpressionState.operatorIsInsertable) {
            currentExpressionState.expression += clickedView.text
            currentExpressionState.operatorIsInsertable = false
        }
    }

    private fun clear(currentExpression: MathExpression) {
        currentExpression.setDefaults()
    }

    private fun calculate(currentExpressionState: MathExpression): MathExpression {
        var formattedInfixExpression = formatter.formatExpression(currentExpressionState)
        var result = calculator.processInfixExpression(formattedInfixExpression)

        if (formatter.isValidNumber(result)) {
            if (formatter.parseStringToDouble(result) < 0) {

                // We must enclose it in brackets for further subsequent processing
                result = "(" + result + ")"
            }
        }

        currentExpressionState.expression = result
        return currentExpressionState
    }
}