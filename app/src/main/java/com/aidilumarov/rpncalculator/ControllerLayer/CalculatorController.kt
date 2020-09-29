package com.aidilumarov.rpncalculator.ControllerLayer

import android.view.View
import android.widget.TextView
import com.aidilumarov.rpncalculator.ModelLayer.CalculatorModel
import com.aidilumarov.rpncalculator.ViewLayer.CalculatorViewActivity

public class CalculatorController : View.OnClickListener {

    private val calculatorModel: CalculatorModel

    public constructor(calculatorView: CalculatorViewActivity) {
        calculatorModel = CalculatorModel(calculatorView)
    }

    override fun onClick(v: View?) {
        if (v is TextView) {
            calculatorModel.updateOutputViewsBasedOnClick(v)
        }
    }
}