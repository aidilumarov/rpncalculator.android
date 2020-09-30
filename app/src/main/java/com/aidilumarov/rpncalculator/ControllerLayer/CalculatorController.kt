package com.aidilumarov.rpncalculator.ControllerLayer

import android.view.View
import android.widget.TextView
import com.aidilumarov.rpncalculator.ModelLayer.core.CalculatorAction
import com.aidilumarov.rpncalculator.ModelLayer.CalculatorModel
import com.aidilumarov.rpncalculator.ViewLayer.CalculatorViewActivity

public class CalculatorController : View.OnClickListener {

    private val calculatorModel: CalculatorModel

    public constructor(calculatorView: CalculatorViewActivity) {
        calculatorModel = CalculatorModel(calculatorView)
    }

    override fun onClick(v: View?) {
        if (v is TextView) {
            when (v.text) {

                // If brackets were clicked
                CalculatorAction.OPENING_BRACKET.stringValue
                -> calculatorModel.updateOutputViewsBasedOnClick(CalculatorAction.OPENING_BRACKET)
                CalculatorAction.CLOSING_BRACKET.stringValue
                -> calculatorModel.updateOutputViewsBasedOnClick(CalculatorAction.CLOSING_BRACKET)

                // If clear was clicked
                CalculatorAction.CLEAR.stringValue
                -> calculatorModel.updateOutputViewsBasedOnClick(CalculatorAction.CLEAR)

                // If numbers were clicked
                CalculatorAction.VALUE_ZERO.stringValue
                -> calculatorModel.updateOutputViewsBasedOnClick(CalculatorAction.VALUE_ZERO)
                CalculatorAction.VALUE_ONE.stringValue
                -> calculatorModel.updateOutputViewsBasedOnClick(CalculatorAction.VALUE_ONE)
                CalculatorAction.VALUE_TWO.stringValue
                -> calculatorModel.updateOutputViewsBasedOnClick(CalculatorAction.VALUE_TWO)
                CalculatorAction.VALUE_THREE.stringValue
                -> calculatorModel.updateOutputViewsBasedOnClick(CalculatorAction.VALUE_THREE)
                CalculatorAction.VALUE_FOUR.stringValue
                -> calculatorModel.updateOutputViewsBasedOnClick(CalculatorAction.VALUE_FOUR)
                CalculatorAction.VALUE_FIVE.stringValue
                -> calculatorModel.updateOutputViewsBasedOnClick(CalculatorAction.VALUE_FIVE)
                CalculatorAction.VALUE_SIX.stringValue
                -> calculatorModel.updateOutputViewsBasedOnClick(CalculatorAction.VALUE_SIX)
                CalculatorAction.VALUE_SEVEN.stringValue
                -> calculatorModel.updateOutputViewsBasedOnClick(CalculatorAction.VALUE_SEVEN)
                CalculatorAction.VALUE_EIGHT.stringValue
                -> calculatorModel.updateOutputViewsBasedOnClick(CalculatorAction.VALUE_EIGHT)
                CalculatorAction.VALUE_NINE.stringValue
                -> calculatorModel.updateOutputViewsBasedOnClick(CalculatorAction.VALUE_NINE)

                // If point was clicked
                CalculatorAction.VALUE_POINT.stringValue
                -> calculatorModel.updateOutputViewsBasedOnClick(CalculatorAction.VALUE_POINT)

                // If an operator was clicked
                CalculatorAction.OPERATOR_PLUS.stringValue
                -> calculatorModel.updateOutputViewsBasedOnClick(CalculatorAction.OPERATOR_PLUS)
                CalculatorAction.OPERATOR_MINUS.stringValue
                -> calculatorModel.updateOutputViewsBasedOnClick(CalculatorAction.OPERATOR_MINUS)
                CalculatorAction.OPERATOR_MULTIPLY.stringValue
                -> calculatorModel.updateOutputViewsBasedOnClick(CalculatorAction.OPERATOR_MULTIPLY)
                CalculatorAction.OPERATOR_DIVIDE.stringValue
                -> calculatorModel.updateOutputViewsBasedOnClick(CalculatorAction.OPERATOR_DIVIDE)
                CalculatorAction.OPERATOR_POWER.stringValue
                -> calculatorModel.updateOutputViewsBasedOnClick(CalculatorAction.OPERATOR_POWER)

                // If = was clicked
                CalculatorAction.CALCULATE.stringValue
                -> calculatorModel.updateOutputViewsBasedOnClick(CalculatorAction.CALCULATE)
            }
        }
    }
}