package com.aidilumarov.rpncalculator.ModelLayer.core

enum class CalculatorAction(val stringValue: String) {
    OPERATOR_PLUS("+"),
    OPERATOR_MINUS("-"),
    OPERATOR_MULTIPLY("*"),
    OPERATOR_DIVIDE("/"),
    OPERATOR_POWER("^"),
    OPENING_BRACKET("("),
    CLOSING_BRACKET(")"),
    VALUE_POINT("."),
    VALUE_ZERO("0"),
    VALUE_ONE("1"),
    VALUE_TWO("2"),
    VALUE_THREE("3"),
    VALUE_FOUR("4"),
    VALUE_FIVE("5"),
    VALUE_SIX("6"),
    VALUE_SEVEN("7"),
    VALUE_EIGHT("8"),
    VALUE_NINE("9"),
    CLEAR("C"),
    CALCULATE("=")
}