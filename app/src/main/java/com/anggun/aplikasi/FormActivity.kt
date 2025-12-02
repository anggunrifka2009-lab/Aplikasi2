package com.anggun.aplikasi

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.collections.joinToString
import kotlin.jvm.java

class FormActivity : AppCompatActivity() {
    // membuat variabel
    lateinit var etnama: EditText
    lateinit var etnohp: EditText
    lateinit var etalamat: EditText
    lateinit var spnagama: Spinner
    lateinit var rblk: RadioButton
    lateinit var rbpr: RadioButton
    lateinit var cbread: CheckBox
    lateinit var cbeat: CheckBox
    lateinit var cbsleep: CheckBox
    lateinit var cbsport: CheckBox
    lateinit var btsimpan: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_form)
        init()

        val hobi = mutableListOf<String>()

        btsimpan.setOnClickListener{
            val nama = etnama.text
            val nohp = etnohp.text
            val alamat = etalamat.text

            //if (nama.isEmpty()){
                //etnama.error = "Nama harus di isi!"
               // return@setOnClickListener
            //}

            val agama = spnagama.selectedItem.toString()
            val adapter = ArrayAdapter.createFromResource(
                this, R.array.agama, android.R.layout.simple_spinner_item
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spnagama.adapter = ArrayAdapter.createFromResource(
                    this, R.array.agama, android.R.layout.simple_spinner_item
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spnagama.adapter = adapter

            val jnsklmn = if (rbpr.isChecked) "Perempuan" else "Laki-laki"

            hobi.clear()
            if (cbread.isChecked) hobi.add("Membaca")
            if (cbeat.isChecked) hobi.add("Makan")
            if (cbsleep.isChecked) hobi.add("Tidur")
            if (cbsport.isChecked) hobi.add("Olahraga")

            val hobi = if (hobi.isEmpty()) "Tidak ada" else hobi.joinToString(", ")


            //7. berpindah ke activity hasil form
            val keHasil = Intent(this, HasilFormActivity::class.java)
            //8. menyertakan data untuk di kirim
            keHasil.putExtra("nama", nama.toString())
            keHasil.putExtra("nohp", nohp.toString())
            keHasil.putExtra("alamat", alamat.toString())
            keHasil.putExtra("agama", agama)
            keHasil.putExtra("jeniskelamin", jnsklmn)
            keHasil.putExtra("hobi", hobi.toString())


            startActivity(keHasil)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
    fun init(){
        etnama = findViewById(R.id.etNama)
        etnohp = findViewById(R.id.etNoHP)
        etalamat = findViewById(R.id.etAlamat)
        spnagama = findViewById(R.id.spnAgama)
        rblk = findViewById(R.id.rbLk)
        rbpr = findViewById(R.id.rbPr)
        cbread = findViewById(R.id.cbRead)
        cbeat = findViewById(R.id.cbEat)
        cbsleep = findViewById(R.id.cbSleep)
        cbsport = findViewById(R.id.cbSport)
        btsimpan = findViewById(R.id.btSimpan)


    }

}