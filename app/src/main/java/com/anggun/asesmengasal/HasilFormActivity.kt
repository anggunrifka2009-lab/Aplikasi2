package com.anggun.asesmengasal

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.toString

class HasilFormActivity : AppCompatActivity() {
    lateinit var tvnama: TextView
    lateinit var tvnohp: TextView
    lateinit var tvalamat: TextView
    lateinit var tvagama: TextView
    lateinit var tvjnsklmn: TextView
    lateinit var tvhobi: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_hasil_form)
        init()

        getData()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun init(){
        tvnama = findViewById(R.id.tvNama)
        tvnohp = findViewById(R.id.tvNoHP)
        tvalamat = findViewById(R.id.tvAlamat)
        tvagama = findViewById(R.id.tvAgama)
        tvjnsklmn = findViewById(R.id.tvJnsKlmn)
        tvhobi = findViewById(R.id.tvHobi)
    }
    fun getData(){
        val nama = intent.getStringExtra("nama").toString()
        val nohp = intent.getStringExtra("nohp").toString()
        val alamat = intent.getStringExtra("alamat").toString()
        val agama = intent.getStringExtra("agama").toString()
        val jnsklmn = intent.getStringExtra("jeniskelamin").toString()
        val hobi = intent.getStringExtra("hobi").toString()



        tvnama.text = "Nama: $nama"
        tvnohp.text = "No HP: $nohp"
        tvalamat.text = "Alamat: $alamat"
        tvagama.text = "Agama: $agama"
        tvjnsklmn.text = "Jenis Kelamin: $jnsklmn"
        tvhobi.text = "Hobi: $hobi"



    }
}