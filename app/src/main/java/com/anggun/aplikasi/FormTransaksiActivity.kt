package com.anggun.aplikasi

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import kotlin.jvm.java
import kotlin.text.toIntOrNull

class FormTransaksiActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_transaksi)

        // Ambil edittext sesuai XML baru
        val etNam = findViewById<EditText>(R.id.etNam)

        val etNasi = findViewById<EditText>(R.id.etNasi)
        val etAyam = findViewById<EditText>(R.id.etAyam)
        val etKangkung = findViewById<EditText>(R.id.etKangkung)
        val etTempe = findViewById<EditText>(R.id.etTempe)
        val etMie = findViewById<EditText>(R.id.etMie)

        val etTeh = findViewById<EditText>(R.id.etTeh)
        val etAnget = findViewById<EditText>(R.id.etAnget)
        val etLemon = findViewById<EditText>(R.id.etLemon)
        val etCincau = findViewById<EditText>(R.id.etCincau)
        val etNutri = findViewById<EditText>(R.id.etNutri)
        val btnHitung = findViewById<Button>(R.id.btnHitung)

        btnHitung.setOnClickListener {

            // ambil nama
            val nama = etNam.text.toString()

            // ambil jumlah, kalau kosong → 0
            val nasi = etNasi.text.toString().toIntOrNull() ?: 0
            val ayam = etAyam.text.toString().toIntOrNull() ?: 0
            val kangkung = etKangkung.text.toString().toIntOrNull() ?: 0
            val tempe = etTempe.text.toString().toIntOrNull() ?: 0
            val mie = etMie.text.toString().toIntOrNull() ?: 0

            val teh = etTeh.text.toString().toIntOrNull() ?: 0
            val anget = etAnget.text.toString().toIntOrNull() ?: 0
            val lemon = etLemon.text.toString().toIntOrNull() ?: 0
            val cincau = etCincau.text.toString().toIntOrNull() ?: 0
            val nutri = etNutri.text.toString().toIntOrNull() ?: 0


            // kirim data ke nota
            val intent = Intent(this, NotaTransaksi::class.java)

            intent.putExtra("nama", nama)

            intent.putExtra("nasi", nasi)
            intent.putExtra("ayam", ayam)
            intent.putExtra("kangkung", kangkung)
            intent.putExtra("tempe", tempe)
            intent.putExtra("mie", mie)

            intent.putExtra("teh", teh)
            intent.putExtra("anget", anget)
            intent.putExtra("lemon", lemon)
            intent.putExtra("cincau", cincau)
            intent.putExtra("nutri", nutri)

            startActivity(intent)
        }
    }
}
