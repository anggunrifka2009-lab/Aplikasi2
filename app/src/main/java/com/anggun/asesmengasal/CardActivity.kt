package com.anggun.asesmengasal

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.jvm.java

class CardActivity : AppCompatActivity() {
    //langkah 1 membuat variabel
    lateinit var homecard: CardView
    lateinit var helpdeskcard: CardView
    lateinit var pegawaicard: CardView
    lateinit var galericard: CardView
    lateinit var surveycard: CardView
    lateinit var exitcard: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_card)
        //langkah 4 memanggil fun init
        init()
        //langkah 5 menambahkan mode onClick pada cv
        homecard.setOnClickListener{
            Toast.makeText(this@CardActivity,"Form Diklik", Toast.LENGTH_SHORT).show() //saaat di klik ada pop up nya

            startActivity(Intent(this, FormActivity::class.java)) // cara pertama - simple //pindah activity saat di klik
            // cara kedua - agak ribet tapi ada kelebihan bisa di tambah kan data untuk di kirim di activity berikutnya
            val intent = Intent(this@CardActivity, FormActivity::class.java)
            intent.putExtra("Nama", "Anggun")
            intent.putExtra("Kelas", "XI RPL")
            startActivity(intent)
        }
        helpdeskcard.setOnClickListener{
            Toast.makeText(this@CardActivity,"Form Transaksi Diklik", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, FormTransaksiActivity::class.java))
        }
        pegawaicard.setOnClickListener{
            Toast.makeText(this@CardActivity,"Kalkulator Diklik", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, Kalkulator::class.java))
        }
        galericard.setOnClickListener{
            Toast.makeText(this@CardActivity,"Konversi Suhu Diklik", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, KonversiSuhu::class.java))
        }
        surveycard.setOnClickListener{
            Toast.makeText(this@CardActivity,"Profie Diklik", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, Profile::class.java))
        }
        exitcard.setOnClickListener {
            val builder = androidx.appcompat.app.AlertDialog.Builder(this)
            builder.setTitle("Konfirmasi")
            builder.setMessage("Apakah kamu yakin ingin keluar?")
            builder.setPositiveButton("Ya") { _, _ ->
                finishAffinity()
            }
            builder.setNegativeButton("Tidak") { dialog, _ ->
                dialog.dismiss()
            }
            builder.show()

    }
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    //langkah 2 membuat fun baru
    fun init(){
        //langkah 3 mengisikan variable
        homecard = findViewById(R.id.homeCard)
        helpdeskcard = findViewById(R.id.helpdeskCard)
        pegawaicard = findViewById(R.id.pegawaiCard)
        galericard = findViewById(R.id.galeriCard)
        surveycard = findViewById(R.id.surveyCard)
        exitcard = findViewById(R.id.exitCard)
    }
}

class HomeCardActivity {

}
