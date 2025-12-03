package com.anggun.asesmengasal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class KonversiSuhu : AppCompatActivity() {

    private lateinit var etInput: EditText
    private lateinit var spinnerDari: Spinner
    private lateinit var spinnerKe: Spinner
    private lateinit var tvHasil: TextView
    private lateinit var btnKonversi: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_konversi_suhu)

        etInput = findViewById(R.id.etinputSuhu)
        spinnerDari = findViewById(R.id.spinnerDari)
        spinnerKe = findViewById(R.id.spinnerKe)
        tvHasil = findViewById(R.id.tvHasilSuhu)
        btnKonversi = findViewById(R.id.btnKonversi)

        // Isi spinner
        val satuan = arrayOf("Celsius", "Fahrenheit", "Kelvin", "Reamur")

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, satuan)
        spinnerDari.adapter = adapter
        spinnerKe.adapter = adapter

        btnKonversi.setOnClickListener {
            konversiSuhu()
        }
    }

    private fun konversiSuhu() {
        val inputText = etInput.text.toString()

        if (inputText.isEmpty()) {
            tvHasil.text = "Isi suhu dulu!"
            return
        }

        val nilaiAwal = inputText.toDouble()
        val dari = spinnerDari.selectedItem.toString()
        val ke = spinnerKe.selectedItem.toString()

        val hasil = convert(dari, ke, nilaiAwal)

        tvHasil.text = "$hasil $ke"
    }

    private fun convert(dari: String, ke: String, nilai: Double): Double {

        // 1. Ubah ke Celsius dulu
        val celsius = when (dari) {
            "Celsius" -> nilai
            "Fahrenheit" -> (nilai - 32) * 5/9
            "Kelvin" -> nilai - 273.15
            "Reamur" -> nilai * 5/4
            else -> nilai
        }

        // 2. Dari Celsius → satud tujuan
        return when (ke) {
            "Celsius" -> celsius
            "Fahrenheit" -> (celsius * 9/5) + 32
            "Kelvin" -> celsius + 273.15
            "Reamur" -> celsius * 4/5
            else -> celsius
        }
    }
}
