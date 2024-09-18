package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    private lateinit var result: EditText
    private lateinit var calculatorIcon: ImageView
    private var operand1: Double = 0.0
    private var operand2: Double = 0.0
    private var operator: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        result = findViewById(R.id.result)
        calculatorIcon = findViewById(R.id.calculator_icon)

        // Set the custom calculator icon
        calculatorIcon.setImageResource(R.drawable.custom_calculator_icon)

        // Set click listeners for number buttons
        for (i in 0..9) {
            val buttonId = resources.getIdentifier("button_$i", "id", packageName)
            findViewById<Button>(buttonId).setOnClickListener { appendNumber(i.toString()) }
        }

        // Set click listeners for operator buttons
        findViewById<Button>(R.id.button_add).setOnClickListener { setOperator("+") }
        findViewById<Button>(R.id.button_subtract).setOnClickListener { setOperator("-") }
        findViewById<Button>(R.id.button_multiply).setOnClickListener { setOperator("*") }
        findViewById<Button>(R.id.button_divide).setOnClickListener { setOperator("/") }

        findViewById<Button>(R.id.button_equals).setOnClickListener { calculate() }
        findViewById<Button>(R.id.button_clear).setOnClickListener { clear() }
        findViewById<Button>(R.id.button_decimal).setOnClickListener { appendDecimal() }
    }

    private fun appendNumber(number: String) {
        result.append(number)
    }

    private fun setOperator(op: String) {
        operator = op
        operand1 = result.text.toString().toDouble()
        result.setText("")
    }

    private fun calculate() {
        operand2 = result.text.toString().toDouble()
        val resultValue = when (operator) {
            "+" -> operand1 + operand2
            "-" -> operand1 - operand2
            "*" -> operand1 * operand2
            "/" -> operand1 / operand2
            else -> 0.0
        }
        result.setText(resultValue.toString())
    }

    private fun clear() {
        result.setText("")
        operand1 = 0.0
        operand2 = 0.0
        operator = ""
    }

    private fun appendDecimal() {
        if (!result.text.contains(".")) {
            result.append(".")
        }
    }
}