package com.aidilumarov.rpncalculator.ModelLayer

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
        // For bracket manipulation
        openBracketWasTyped = false
        indexOfFirstBracket = null

        // For point manipulation
        pointIsInsertable = true

        // For operator manipulation
        operatorIsInsertable = false
    }

    public fun setDefaults() {
        expression = ""

        // For bracket manipulation
        openBracketWasTyped = false
        indexOfFirstBracket = null

        // For point manipulation
        pointIsInsertable = true

        // For operator manipulation
        operatorIsInsertable = false
    }
}