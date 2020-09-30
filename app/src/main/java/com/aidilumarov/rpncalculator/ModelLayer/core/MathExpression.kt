package com.aidilumarov.rpncalculator.ModelLayer.core

public class MathExpression {

    public var expression: String

    // For bracket manipulation
    public var openBracketWasTyped: Boolean
    public var indexOfFirstBracket: Int?

    // Point manipulation
    public var pointIsInsertable: Boolean

    // Operator manipulation
    public var operatorIsInsertable: Boolean

    public var expressionIsEmpty: Boolean
        get() = expression.isEmpty()

    public constructor() {
        expression = ""
        expressionIsEmpty = expression.isEmpty()
        openBracketWasTyped = false
        indexOfFirstBracket = null
        pointIsInsertable = true
        operatorIsInsertable = false
    }

    public fun setDefaults() {
        expression = ""
        openBracketWasTyped = false
        indexOfFirstBracket = null
        pointIsInsertable = true
        operatorIsInsertable = false
    }
}