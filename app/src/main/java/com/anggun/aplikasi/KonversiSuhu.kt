package com.anggun.aplikasi

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class KonversiSuhu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_konversi_suhu)

        // Untuk padding sistem (biarin, ini bawaan)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // ============================
        //   SAMBUNGKAN XML KE KOTLIN
        // ============================
        val inputSuhu = findViewById<EditText>(R.id.inputSuhu)
        val spinnerDari = findViewById<Spinner>(R.id.spinnerDari)
        val spinnerKe = findViewById<Spinner>(R.id.spinnerKe)
        val btnKonversi = findViewById<Button>(R.id.btnKonversi)
        val hasil = findViewById<TextView>(R.id.hasilKonversi)

        // ============================
        //       ISI SPINNER
        // ============================
        val satuan = arrayOf("Celsius", "Fahrenheit", "Kelvin")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, satuan)

        spinnerDari.adapter = adapter
        spinnerKe.adapter = adapter

        // ============================
        //     LOGIKA TOMBOL KONVERSI
        // ============================
        btnKonversi.setOnClickListener {

            val nilaiInput = inputSuhu.text.toString()

            // Cek input kosong
            if (nilaiInput.isEmpty()) {
                hasil.text = "Harap isi suhu!"
                return@setOnClickListener
            }

            val suhu = nilaiInput.toDouble()
            val dari = spinnerDari.selectedItem.toString()
            val ke = spinnerKe.selectedItem.toString()

            val output = konversiSuhu(suhu, dari, ke)

            hasil.text = "$suhu $dari = $output $ke"
        }
    }

    // ============================
    //     FUNGSI KONVERSI SUHU
    // ============================
    private fun konversiSuhu(nilai: Double, dari: String, ke: String): Double {

        // Semua dikonversi jadi Celsius dulu
        val celsius = when (dari) {
            "Celsius" -> nilai
            "Fahrenheit" -> (nilai - 32) * 5/9
            "Kelvin" -> nilai - 273.15
            else -> nilai
        }

        // Dari Celsius ke tujuan
        return when (ke) {
            "Celsius" -> celsius
            "Fahrenheit" -> (celsius * 9/5) + 32
            "Kelvin" -> celsius + 273.15
            else -> celsius
        }
    }
}