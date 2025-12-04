package com.anggun.asesmengasal

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.Stack

class Kalkulator : AppCompatActivity() {

    private lateinit var tvHasil: TextView
    private lateinit var tvHasil2: TextView

    private lateinit var btn0: Button
    private lateinit var btn00: Button
    private lateinit var btn1: Button
    private lateinit var btn2: Button
    private lateinit var btn3: Button
    private lateinit var btn4: Button
    private lateinit var btn5: Button
    private lateinit var btn6: Button
    private lateinit var btn7: Button
    private lateinit var btn8: Button
    private lateinit var btn9: Button

    private lateinit var btnKoma: Button
    private lateinit var btnPlus: Button
    private lateinit var btnKurang: Button
    private lateinit var btnKali: Button
    private lateinit var btnBagi: Button

    private lateinit var btnC: Button
    private lateinit var btnDel: Button
    private lateinit var btnSaDeng: Button

    private var expression = ""
    private var currentNumber = ""
    private var isNewCalc = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kalkulator)

        initViews()
        setupNumberButtons()
        setupOperatorButtons()
        setupSpecialButtons()
    }

    private fun initViews() {
        tvHasil = findViewById(R.id.tvHasil)
        tvHasil2 = findViewById(R.id.tvHasil2)

        btn0 = findViewById(R.id.btn0)
        btn00 = findViewById(R.id.btn00)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        btn4 = findViewById(R.id.btn4)
        btn5 = findViewById(R.id.btn5)
        btn6 = findViewById(R.id.btn6)
        btn7 = findViewById(R.id.btn7)
        btn8 = findViewById(R.id.btn8)
        btn9 = findViewById(R.id.btn9)

        btnKoma = findViewById(R.id.btnKoma)
        btnPlus = findViewById(R.id.btnPlus)
        btnKurang = findViewById(R.id.btnKurang)
        btnKali = findViewById(R.id.btnKali)
        btnBagi = findViewById(R.id.btnBagi)

        btnC = findViewById(R.id.btnC)
        btnDel = findViewById(R.id.btnDel)
        btnSaDeng = findViewById(R.id.btnSaDeng)
    }

    private fun setupNumberButtons() {
        val click = { num: String ->
            if (isNewCalc) {
                expression = ""
                currentNumber = ""
                isNewCalc = false
            }
            currentNumber += num
            updateDisplay()
        }

        btn0.setOnClickListener { click("0") }
        btn00.setOnClickListener { click("00") }
        btn1.setOnClickListener { click("1") }
        btn2.setOnClickListener { click("2") }
        btn3.setOnClickListener { click("3") }
        btn4.setOnClickListener { click("4") }
        btn5.setOnClickListener { click("5") }
        btn6.setOnClickListener { click("6") }
        btn7.setOnClickListener { click("7") }
        btn8.setOnClickListener { click("8") }
        btn9.setOnClickListener { click("9") }

        btnKoma.setOnClickListener {
            if (!currentNumber.contains(".")) {
                if (currentNumber.isEmpty()) currentNumber = "0"
                currentNumber += "."
                updateDisplay()
            }
        }
    }

    private fun setupOperatorButtons() {

        val opClick = { op: String ->
            if (currentNumber.isNotEmpty()) {
                expression += currentNumber
                currentNumber = ""
            }

            if (expression.isNotEmpty()) {
                if (expression.last() in listOf('+', '-', '×', '÷')) {
                    expression = expression.dropLast(1)
                }
                expression += op
                updateDisplay()
            }
        }

        btnPlus.setOnClickListener { opClick("+") }
        btnKurang.setOnClickListener { opClick("-") }
        btnKali.setOnClickListener { opClick("×") }
        btnBagi.setOnClickListener { opClick("÷") }

        btnSaDeng.setOnClickListener { calculateResult() }
    }

    private fun setupSpecialButtons() {

        btnC.setOnClickListener {
            expression = ""
            currentNumber = ""
            tvHasil.text = "0"
            tvHasil2.text = ""
        }

        btnDel.setOnClickListener {
            if (currentNumber.isNotEmpty()) {
                currentNumber = currentNumber.dropLast(1)
            } else if (expression.isNotEmpty()) {
                expression = expression.dropLast(1)
            }
            updateDisplay()

            if (currentNumber.isEmpty() && expression.isEmpty()) {
                tvHasil.text = "0"
            }
        }
    }

    private fun updateDisplay() {
        val text = expression + currentNumber
        tvHasil.text = if (text.isEmpty()) "0" else text
    }

    private fun calculateResult() {
        try {
            if (currentNumber.isNotEmpty()) {
                expression += currentNumber
            }

            if (expression.isEmpty()) return

            val result = evaluateExpression(expression)

            tvHasil2.text = "$expression ="
            tvHasil.text = formatNumber(result)

            expression = result.toString()
            currentNumber = ""
            isNewCalc = true

        } catch (e: Exception) {
            tvHasil2.text = "Error"
            tvHasil.text = "0"
            expression = ""
            currentNumber = ""
        }
    }

    private fun evaluateExpression(expr: String): Double {
        var exp = expr.replace("×", "*").replace("÷", "/")

        val numbers = Stack<Double>()
        val ops = Stack<Char>()

        var i = 0
        while (i < exp.length) {
            val c = exp[i]

            when {
                c.isDigit() || c == '.' -> {
                    var num = ""
                    while (i < exp.length && (exp[i].isDigit() || exp[i] == '.')) {
                        num += exp[i]
                        i++
                    }
                    numbers.push(num.toDouble())
                    i--
                }

                c in listOf('+', '-', '*', '/') -> {
                    while (ops.isNotEmpty() && hasPrecedence(c, ops.peek())) {
                        numbers.push(applyOp(ops.pop(), numbers.pop(), numbers.pop()))
                    }
                    ops.push(c)
                }
            }
            i++
        }

        while (ops.isNotEmpty()) {
            numbers.push(applyOp(ops.pop(), numbers.pop(), numbers.pop()))
        }

        return numbers.pop()
    }

    private fun hasPrecedence(op1: Char, op2: Char): Boolean {
        if (op2 == '(' || op2 == ')') return false
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) return false
        return true
    }

    private fun applyOp(op: Char, b: Double, a: Double): Double {
        return when (op) {
            '+' -> a + b
            '-' -> a - b
            '*' -> a * b
            '/' -> {
                if (b == 0.0) throw ArithmeticException("Divide by zero")
                a / b
            }
            else -> 0.0
        }
    }

    private fun formatNumber(number: Double): String {
        return if (number % 1 == 0.0) number.toInt().toString()
        else number.toString()
    }
}
