package com.anggun.asesmengasal

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.text.SimpleDateFormat
import java.util.*

class NotaTransaksi : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_nota_transaksi)

        val tvTgl = findViewById<TextView>(R.id.tvTgl)
        val tvId = findViewById<TextView>(R.id.tvId)
        val tvItem = findViewById<TextView>(R.id.tvItem)
        val tvJumlah = findViewById<TextView>(R.id.tvJumlh)
        val tvSub = findViewById<TextView>(R.id.tvSub)
        val tvTotal = findViewById<TextView>(R.id.tvTotal)
        val tvNoma = findViewById<TextView>(R.id.tvNoma)

        // =============== TANGGAL & WAKTU ================
        val sdf = SimpleDateFormat("dd-MM-yyyy HH:mm", Locale.getDefault())
        tvTgl.text = "Tgl & Waktu : ${sdf.format(Date())}"

        // ================== AMBIL NAMA ===================
        val nama = intent.getStringExtra("nama") ?: ""
        tvNoma.text = "Nama : $nama"

        // ================== AMBIL DATA ===================
        val nasi = intent.getIntExtra("nasi", 0)
        val ayam = intent.getIntExtra("ayam", 0)
        val kangkung = intent.getIntExtra("kangkung", 0)
        val tempe = intent.getIntExtra("tempe", 0)
        val mie = intent.getIntExtra("mie", 0)

        val teh = intent.getIntExtra("teh", 0)
        val anget = intent.getIntExtra("anget", 0)
        val lemon = intent.getIntExtra("lemon", 0)
        val cincau = intent.getIntExtra("cincau", 0)
        val nutri = intent.getIntExtra("nutri", 0)

        // daftar item + harga
        val items = listOf(
            Triple("Nasi", nasi, 3500),
            Triple("Ayam Goreng", ayam, 8000),
            Triple("Tumis Kangkung", kangkung, 5000),
            Triple("Tempe Krispi", tempe, 4000),
            Triple("Mie Bangladesh", mie, 13000),
            Triple("Es Teh", teh, 3000),
            Triple("Teh Anget", anget, 6000),
            Triple("Lemon Tea", lemon, 4000),
            Triple("Es Cincau", cincau, 6000),
            Triple("Nutrisari", cincau, 4000)
        )

        var nomor = ""
        var namaItem = ""
        var jumlahItem = ""
        var subtotalItem = ""
        var totalSemua = 0
        var counter = 1

        // loop tampilan hanya kalau jumlah > 0
        for ((namaMakanan, jumlah, harga) in items) {
            if (jumlah > 0) {
                val sub = jumlah * harga

                nomor += "$counter\n"
                namaItem += "$namaMakanan\n"
                jumlahItem += "$jumlah\n"
                subtotalItem += "Rp $sub\n"

                totalSemua += sub
                counter++
            }
        }

        tvId.text = nomor
        tvItem.text = namaItem
        tvJumlah.text = jumlahItem
        tvSub.text = subtotalItem
        tvTotal.text = "TOTAL : Rp $totalSemua"
    }
}
