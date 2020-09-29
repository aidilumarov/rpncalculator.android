package com.aidilumarov.rpncalculator.ModelLayer

import android.view.View
import android.widget.TextView
import com.aidilumarov.rpncalculator.ViewLayer.CalculatorViewActivity

public class CalculatorModel {

    // Reference to view
    private val calculatorView: CalculatorViewActivity

    // Input handler
    private var inputProcessor: InputProcessor

    private val INVALID_EXPRESSION = "Invalid expression"

    public constructor(calculatorView: CalculatorViewActivity) {
        this.calculatorView = calculatorView
        inputProcessor = InputProcessor(calculatorView)
    }

    public fun updateOutputViewsBasedOnClick(clickableView: TextView?) {
        if (clickableView == null) {
            return
        }

        var updatedOutput = inputProcessor.getUpdatedOutputBasedOnClick(clickableView)
        clearOutput(calculatorView.resultView)

        if (clickableView == calculatorView.buttonCalculate) {
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