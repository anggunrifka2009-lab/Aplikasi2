package com.anggun.asesmengasal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class Kalkulator : AppCompatActivity() {

    private lateinit var tvHasil: TextView
    private lateinit var tvOperasi: TextView

    private var input = ""
    private var operator = ""
    private var angkaPertama = ""
    private var selesai = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_kalkulator)

        tvHasil = findViewById(R.id.tvHasil)
        tvOperasi = findViewById(R.id.tvHasil2)

        val angkaButtons = mapOf(
            R.id.btn00 to "00",
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
                if (selesai) return@setOnClickListener
                input += angka
                tvHasil.text = input
            }
        }

        findViewById<Button>(R.id.btnPlus).setOnClickListener { pilihOperator("+") }
        findViewById<Button>(R.id.btnKurang).setOnClickListener { pilihOperator("-") }
        findViewById<Button>(R.id.btnKali).setOnClickListener { pilihOperator("×") }
        findViewById<Button>(R.id.btnBagi).setOnClickListener { pilihOperator("÷") }

        findViewById<Button>(R.id.btnKoma).setOnClickListener {
            if (!input.contains(".")) {
                input += "."
                tvHasil.text = input
            }
        }

        findViewById<Button>(R.id.btnC).setOnClickListener {
            input = ""
            operator = ""
            angkaPertama = ""
            tvOperasi.text = ""
            tvHasil.text = "0"
            selesai = false
        }

        findViewById<Button>(R.id.btnDel).setOnClickListener {
            if (input.isNotEmpty()) {
                input = input.dropLast(1)
                selesai = false
                tvHasil.text = if (input.isEmpty()) "0" else input
            }
        }

        findViewById<Button>(R.id.btnSaDeng).setOnClickListener {
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
            selesai = false

            // Menampilkan operasi seperti "5 -"
            tvOperasi.text = "$angkaPertama $operator"
        }
    }

    private fun hitungHasil() {
        val a = angkaPertama.toDouble()
        val b = input.toDouble()
        val hasil = when (operator) {
            "+" -> a + b
            "-" -> a - b
            "×" -> a * b
            "÷" -> if (b == 0.0) 0.0 else a / b
            else -> 0.0
        }

        // Tampilkan operasi lengkap: "5 - 4 ="
        tvOperasi.text = "$angkaPertama $operator $input ="

        // Hasil besar seperti di gambar
        tvHasil.text = hasil.toString()

        input = hasil.toString()
        angkaPertama = ""
        operator = ""

        selesai = true
    }
}
