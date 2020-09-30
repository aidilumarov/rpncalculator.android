package com.aidilumarov.rpncalculator.ModelLayer.core

class InputProcessor {
    private var mathExpression: MathExpression
    private val formatter: MathExpressionFormatter
    private val calculator: RPNCalculator

    constructor() {
        mathExpression = MathExpression()
        formatter = MathExpressionFormatter()
        calculator = RPNCalculator()
    }

    fun clear() {
        clear(mathExpression)
    }

    fun getUpdatedOutputBasedOnClick(action: CalculatorAction) : MathExpression {

        when (action) {

            // If brackets were clicked
            CalculatorAction.OPENING_BRACKET -> processBrackets(action, mathExpression)
            CalculatorAction.CLOSING_BRACKET -> processBrackets(action, mathExpression)

            // If clear was clicked
            CalculatorAction.CLEAR -> clear(mathExpression)

            // If numbers were clicked
            CalculatorAction.VALUE_ZERO -> processNumbers(action, mathExpression)
            CalculatorAction.VALUE_ONE -> processNumbers(action, mathExpression)
            CalculatorAction.VALUE_TWO -> processNumbers(action, mathExpression)
            CalculatorAction.VALUE_THREE -> processNumbers(action, mathExpression)
            CalculatorAction.VALUE_FOUR -> processNumbers(action, mathExpression)
            CalculatorAction.VALUE_FIVE -> processNumbers(action, mathExpression)
            CalculatorAction.VALUE_SIX -> processNumbers(action, mathExpression)
            CalculatorAction.VALUE_SEVEN -> processNumbers(action, mathExpression)
            CalculatorAction.VALUE_EIGHT -> processNumbers(action, mathExpression)
            CalculatorAction.VALUE_NINE -> processNumbers(action, mathExpression)

            // If point was clicked
            CalculatorAction.VALUE_POINT -> processPoint(action, mathExpression)

            // If an operator was clicked
            CalculatorAction.OPERATOR_PLUS -> processOperators(action, mathExpression)
            CalculatorAction.OPERATOR_MINUS -> processOperators(action, mathExpression)
            CalculatorAction.OPERATOR_MULTIPLY -> processOperators(action, mathExpression)
            CalculatorAction.OPERATOR_DIVIDE -> processOperators(action, mathExpression)
            CalculatorAction.OPERATOR_POWER -> processOperators(action, mathExpression)

            // If = was clicked
            CalculatorAction.CALCULATE -> calculate(mathExpression)
        }

        return mathExpression
    }

    private fun processBrackets(action: CalculatorAction, currentExpressionState: MathExpression) {

        if (action == CalculatorAction.CLOSING_BRACKET) {

            // If closing bracket was clicked without opening a bracket
            // or closing bracket was clicked immediately after opening, return
            if (!currentExpressionState.openBracketWasTyped
                || currentExpressionState.expression.last().toString() == action.stringValue) {
                return
            }
        } else {
            if (!currentExpressionState.openBracketWasTyped) {
                currentExpressionState.indexOfFirstBracket = currentExpressionState.expression.length
                currentExpressionState.openBracketWasTyped = true
            }
        }

        currentExpressionState.expression += action.stringValue
    }

    private fun processNumbers(action: CalculatorAction, currentExpressionState: MathExpression) {

        if (!currentExpressionState.expressionIsEmpty
            && action == CalculatorAction.VALUE_ZERO
            && currentExpressionState.pointIsInsertable) {
            if (action.stringValue == currentExpressionState.expression.last().toString()) {
                return
            }
        }

        currentExpressionState.expression += action.stringValue
        currentExpressionState.operatorIsInsertable = true
    }

    private fun processPoint(action: CalculatorAction, currentExpressionState: MathExpression) {

        // If last character in the expression is not digit, then 0 should be prefixed
        if (currentExpressionState.pointIsInsertable) {
            if (currentExpressionState.expressionIsEmpty
                || !currentExpressionState.expression.last().isDigit()) {
                currentExpressionState.expression += "0" + action.stringValue
                currentExpressionState.pointIsInsertable = false
            } else {
                    currentExpressionState.expression += action.stringValue
                    currentExpressionState.pointIsInsertable = false
                }
            }
    }

    private fun processOperators(action: CalculatorAction, currentExpressionState: MathExpression) {

        // If - is clicked, we must check whether or not brackets were opened
        if (action == CalculatorAction.OPERATOR_MINUS) {
            if (currentExpressionState.openBracketWasTyped) {
                currentExpressionState.expression += action.stringValue
                return
            }
        }

        if (currentExpressionState.operatorIsInsertable) {
            currentExpressionState.expression += action.stringValue
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