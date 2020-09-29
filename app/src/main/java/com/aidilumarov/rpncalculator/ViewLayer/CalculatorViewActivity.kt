package com.aidilumarov.rpncalculator.ViewLayer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.aidilumarov.rpncalculator.ControllerLayer.CalculatorController
import com.aidilumarov.rpncalculator.R
import kotlinx.android.synthetic.*

class CalculatorViewActivity : AppCompatActivity() {

    private lateinit var calculatorController: CalculatorController;

    // Store reference to all buttons
    private lateinit var clickables: ArrayList<View>

    // Output views
    public lateinit var expressionView: TextView
    public lateinit var resultView: TextView

    // Numeric Buttons
    public lateinit var buttonZero: TextView
    public lateinit var buttonOne: TextView
    public lateinit var buttonTwo: TextView
    public lateinit var buttonThree: TextView
    public lateinit var buttonFour: TextView
    public lateinit var buttonFive: TextView
    public lateinit var buttonSix: TextView
    public lateinit var buttonSeven: TextView
    public lateinit var buttonEight: TextView
    public lateinit var buttonNine: TextView
    public lateinit var buttonPoint: TextView

    // Operator buttons
    public lateinit var buttonPlus: TextView
    public lateinit var buttonMinus: TextView
    public lateinit var buttonMultiply: TextView
    public lateinit var buttonDivide: TextView
    public lateinit var buttonPower: TextView
    public lateinit var buttonCalculate: TextView


    // Other buttons
    public lateinit var buttonOpenBrackets: TextView
    public lateinit var buttonCloseBrackets: TextView
    public lateinit var buttonClearScreen: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize controller
        calculatorController = CalculatorController(this)

        initializeViews()
        setOnClickListeners(this.clickables)

    }

    private fun initializeViews() {
        // Initialize output views
        expressionView = findViewById(R.id.textview_expressionOutput)
        resultView = findViewById(R.id.textview_resultOutput)
        clickables = ArrayList<View>(14)

        // Initialize numeric buttons
        buttonZero = findViewById(R.id.button_zero); clickables.add(buttonZero)
        buttonOne = findViewById(R.id.button_one); clickables.add(buttonOne)
        buttonTwo = findViewById(R.id.button_two); clickables.add(buttonTwo)
        buttonThree = findViewById(R.id.button_three); clickables.add(buttonThree)
        buttonFour = findViewById(R.id.button_four); clickables.add(buttonFour)
        buttonFive = findViewById(R.id.button_five); clickables.add(buttonFive)
        buttonSix = findViewById(R.id.button_six); clickables.add(buttonSix)
        buttonSeven = findViewById(R.id.button_seven); clickables.add(buttonSeven)
        buttonEight = findViewById(R.id.button_eight); clickables.add(buttonEight)
        buttonNine = findViewById(R.id.button_nine); clickables.add(buttonNine)
        buttonPoint = findViewById(R.id.button_point); clickables.add(buttonPoint)

        // Initialize operator buttons
        buttonPlus = findViewById(R.id.button_plus); clickables.add(buttonPlus)
        buttonMinus = findViewById(R.id.button_minus); clickables.add(buttonMinus)
        buttonMultiply = findViewById(R.id.button_multiply); clickables.add(buttonMultiply)
        buttonDivide = findViewById(R.id.button_divide); clickables.add(buttonDivide)
        buttonCalculate = findViewById(R.id.button_calculate); clickables.add(buttonCalculate)

        // Initialize other buttons
        buttonOpenBrackets = findViewById(R.id.button_openBrackets); clickables.add(buttonOpenBrackets)
        buttonCloseBrackets = findViewById(R.id.button_closeBrackets); clickables.add(buttonCloseBrackets)
        buttonPower = findViewById(R.id.button_power); clickables.add(buttonPower)
        buttonClearScreen = findViewById(R.id.button_clearScreen); clickables.add(buttonClearScreen)
    }

    private fun setOnClickListeners(clickables: List<View>) {
        for (clickable in clickables) {
            if (clickable.isClickable) {
                clickable.setOnClickListener(calculatorController)
            }
        }
    }
}