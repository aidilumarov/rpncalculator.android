package com.aidilumarov.rpncalculator.ModelLayer;

import java.lang.IllegalArgumentException;
import java.util.EmptyStackException;
import java.util.Stack;

public class RPNCalculator {

    private final String PLUS = "+";
    private final String MINUS = "-";
    private final String MULTIPLY = "*";
    private final String DIVIDE = "/";
    private final String EXPONENTIATION = "^";
    private final String OPENING_BRACKETS = "(";
    private final String CLOSING_BRACKETS = ")";


    /**
     * Accepts an infix mathematical expression and returns result or error message
     * @param infixExpression - Mathematical expression in infix form
     * @return Result of calculation or error message
     */
    public String processInfixExpression(String infixExpression) {
        try {
            String postfixForm = getPostfixExpression(infixExpression);
            return calculate(postfixForm);
        }
        catch (Exception e) {
            return "Invalid expression";
        }
    }

    /**
     * Calculates postfix mathematical expression and returns the result
     * @param postfixExpression - Mathematical expression in postfix form
     * @return Result of calculation
     * @throws IllegalArgumentException if @postfixExpression is not a valid mathematical expression
     */
    private String calculate(String postfixExpression) throws IllegalArgumentException {

        String[] postfixExpressionChunks = postfixExpression.split(" ");

        Stack<Double> operands = new Stack<Double>();

        try {
            for (String chunk : postfixExpressionChunks) {
                if (isNum(chunk)) {
                    operands.push(Double.parseDouble(chunk));
                } else {
                    double operand2 = operands.pop();
                    double operand1 = operands.pop();
                    operands.push(calculate(operand1, operand2, chunk));
                }
            }

            return operands.pop().toString();
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Performs operation on two operands based on operator.
     * @param operand1 - First operand
     * @param operand2 - Second operand
     * @param operator - Operator
     * @return Result of calculation
     * @throws IllegalArgumentException if operator is invalid
     */
     private Double calculate(double operand1, double operand2, String operator) throws IllegalArgumentException {
        switch (operator) {
            case PLUS:
                return operand1 + operand2;
            case MINUS:
                return operand1 - operand2;
            case MULTIPLY:
                return operand1 * operand2;
            case DIVIDE:
                return operand1 / operand2;
            case EXPONENTIATION:
                return Math.pow(operand1, operand2);
        }

        throw new IllegalArgumentException();
    }

    /**
     * Converts infix mathematical expression to postfix form. Invalid expressions are converted incorrectly
     * @param infixExpression - Mathematical expression in infix form
     * @return Mathematical expression in postfix form
     * @throws EmptyStackException if and error occurs somewhere inside. Errors may occur if expression is not valid
     */
    private String getPostfixExpression(String infixExpression) throws EmptyStackException {

        String[] infixExpressionChunks = infixExpression.split(" ");

        Stack<String> operatorsAndBrackets = new Stack<String>();
        StringBuilder postfixExpression = new StringBuilder();

        for (String chunk : infixExpressionChunks) {

            if (isNum(chunk)) {
                postfixExpression.append(chunk + " ");
            }

            else if (chunk.equals(OPENING_BRACKETS)) {
                operatorsAndBrackets.push(chunk);
            }

            else if (chunk.equals(EXPONENTIATION)) {
                operatorsAndBrackets.push(chunk);
            }

            else if (chunk.equals(CLOSING_BRACKETS)) {
                while (!operatorsAndBrackets.empty() && !operatorsAndBrackets.peek().equals(OPENING_BRACKETS)) {
                    postfixExpression.append(operatorsAndBrackets.pop() + " ");
                }

                operatorsAndBrackets.pop();
            }

            else {
                while (!operatorsAndBrackets.empty() &&
                        getPrecedence(chunk) <= getPrecedence(operatorsAndBrackets.peek())) {
                    postfixExpression.append(operatorsAndBrackets.pop() + " ");
                }

                operatorsAndBrackets.push(chunk);
            }
        }

        while (!operatorsAndBrackets.empty()) {
            postfixExpression.append(operatorsAndBrackets.pop() + " ");
        }

        return postfixExpression.toString().trim();
    }

    private int getPrecedence(String mathOperator) {

        if (mathOperator.equals(PLUS) || mathOperator.equals(MINUS)) {
            return 1;
        }

        if (mathOperator.equals(MULTIPLY) || mathOperator.equals(DIVIDE)) {
            return 2;
        }

        if (mathOperator.equals(EXPONENTIATION)) {
            return 3;
        }

        return 0;
    }

    private boolean isNum(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
