package com.anggun.aplikasi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import kotlin.collections.forEach
import kotlin.text.contains
import kotlin.text.dropLast
import kotlin.text.isEmpty
import kotlin.text.isNotEmpty
import kotlin.text.toDouble
import kotlin.to

class Kalkulator : AppCompatActivity() {

    private lateinit var tvHasil: TextView

    private var input = ""
    private var operator = ""
    private var angkaPertama = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kalkulator)

        tvHasil = findViewById(R.id.tvHasil)

        // Angka
        val angkaButtons = mapOf(
            R.id.btn0 to "00",
            R.id.btn0 to "0",
            R.id.btn1 to "1",
            R.id.btn2 to "2",
            R.id.btn3 to "3",
            R.id.btn4 to "4",
            R.id.btn5 to "5",
            R.id.btn6 to "6",
            R.id.btn7 to "7",
            R.id.btn8 to "8",
            R.id.btn9 to "9"
        )

        angkaButtons.forEach { (id, angka) ->
            findViewById<Button>(id).setOnClickListener {
                input += angka
                tvHasil.text = input
            }
        }

        // Operator
        val btnPlus: Button = findViewById(R.id.btnPlus)
        val btnKurang: Button = findViewById(R.id.btnKurang)
        val btnKali: Button = findViewById(R.id.btnKali)
        val btnBagi: Button = findViewById(R.id.btnBagi)

        btnPlus.setOnClickListener { pilihOperator("+") }
        btnKurang.setOnClickListener { pilihOperator("-") }
        btnKali.setOnClickListener { pilihOperator("*") }
        btnBagi.setOnClickListener { pilihOperator("/") }

        // Koma
        val btnKoma: Button = findViewById(R.id.btnKoma)
        btnKoma.setOnClickListener {
            if (!input.contains(".")) {
                input += "."
                tvHasil.text = input
            }
        }

        // Clear
        val btnC: Button = findViewById(R.id.btnC)
        btnC.setOnClickListener {
            input = ""
            angkaPertama = ""
            operator = ""
            tvHasil.text = "0"
        }

        // Delete
        val btnDel: Button = findViewById(R.id.btnDel)
        btnDel.setOnClickListener {
            if (input.isNotEmpty()) {
                input = input.dropLast(1)
                tvHasil.text = if (input.isEmpty()) "0" else input
            }
        }

        // Sama Dengan "="
        val btnSaDeng: Button = findViewById(R.id.btnSaDeng)
        btnSaDeng.setOnClickListener {
            if (angkaPertama.isNotEmpty() && operator.isNotEmpty() && input.isNotEmpty()) {
                hitungHasil()
            }
        }
    }

    private fun pilihOperator(op: String) {
        if (input.isNotEmpty()) {
            angkaPertama = input
            operator = op
            input = ""
        }
    }

    private fun hitungHasil() {
        val a = angkaPertama.toDouble()
        val b = input.toDouble()
        val hasil = when (operator) {
            "+" -> a + b
            "-" -> a - b
            "*" -> a * b
            "/" -> if (b == 0.0) 0.0 else a / b
            else -> 0.0
        }

        tvHasil.text = hasil.toString()

        input = hasil.toString()
        angkaPertama = ""
        operator = ""
    }

}
