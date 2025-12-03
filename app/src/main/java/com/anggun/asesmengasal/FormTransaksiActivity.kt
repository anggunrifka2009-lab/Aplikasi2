package com.anggun.asesmengasal

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class FormTransaksiActivity : AppCompatActivity() {

    private lateinit var etNam: EditText
    private lateinit var etNasi: EditText
    private lateinit var etAyam: EditText
    private lateinit var etKangkung: EditText
    private lateinit var etTempe: EditText
    private lateinit var etMie: EditText

    private lateinit var etTeh: EditText
    private lateinit var etAnget: EditText
    private lateinit var etLemon: EditText
    private lateinit var etCincau: EditText
    private lateinit var etNutri: EditText
    private lateinit var btnHitung: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_form_transaksi)

        etNam = findViewById(R.id.etNam)
        etNasi = findViewById(R.id.etNasi)
        etAyam = findViewById(R.id.etAyam)
        etKangkung = findViewById(R.id.etKangkung)
        etTempe = findViewById(R.id.etTempe)
        etMie = findViewById(R.id.etMie)

        etTeh = findViewById(R.id.etTeh)
        etAnget = findViewById(R.id.etAnget)
        etLemon = findViewById(R.id.etLemon)
        etCincau = findViewById(R.id.etCincau)
        etNutri = findViewById(R.id.etNutri)

        btnHitung = findViewById(R.id.btnHitung)

        btnHitung.setOnClickListener {

            val nama = etNam.text.toString()

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
