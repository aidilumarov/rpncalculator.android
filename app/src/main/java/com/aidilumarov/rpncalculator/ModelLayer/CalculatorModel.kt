package com.aidilumarov.rpncalculator.ModelLayer

import android.content.Context
import android.content.res.AssetManager
import android.widget.TextView
import com.aidilumarov.rpncalculator.ModelLayer.core.CalculatorAction
import com.aidilumarov.rpncalculator.ModelLayer.core.InputProcessor
import com.aidilumarov.rpncalculator.ViewLayer.CalculatorViewActivity
import java.util.*

public class CalculatorModel {

    // Reference to view
    private val calculatorView: CalculatorViewActivity

    // Input handler
    private var inputProcessor: InputProcessor

    private val INVALID_EXPRESSION = "Invalid expression"

    public constructor(calculatorView: CalculatorViewActivity) {
        this.calculatorView = calculatorView
        inputProcessor = InputProcessor()
    }

    public fun updateOutputViewsBasedOnClick(action: CalculatorAction) {

        var updatedOutput = inputProcessor.getUpdatedOutputBasedOnClick(action)
        clearOutput(calculatorView.resultView)

        if (action == CalculatorAction.CALCULATE) {
            calculatorView.resultView.text = updatedOutput.expression

            if (updatedOutput.expression == INVALID_EXPRESSION) {
                inputProcessor.clear()
            }

        } else {
            calculatorView.expressionView.text = updatedOutput.expression
        }
    }

    private fun clearOutput(textView: TextView) {
        textView.text = ""
    }
}