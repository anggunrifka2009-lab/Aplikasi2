package com.anggun.asesmengasal

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class Profile : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        val tvEmail = findViewById<TextView>(R.id.tvEmail)
        val tvNowa = findViewById<TextView>(R.id.tvNowa)
        val tvRumah = findViewById<TextView>(R.id.tvRumah)
        val tvGithub = findViewById<TextView>(R.id.tvGithub)

        // -------- EMAIL -> GMAIL --------
        tvEmail.setOnClickListener {
            val email = tvEmail.text.toString()

            val intent = Intent(Intent.ACTION_SENDTO).apply {
                data = Uri.parse("mailto:$email")
                setPackage("com.google.android.gm")
            }

            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(this, "Aplikasi Gmail tidak ditemukan", Toast.LENGTH_SHORT).show()
            }
        }

        // -------- WA -> WHATSAPP --------
        tvNowa.setOnClickListener {
            val number = tvNowa.text.toString()

            val waIntent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("https://wa.me/$number")
                setPackage("com.whatsapp")
            }

            try {
                startActivity(waIntent)
            } catch (e: Exception) {
                Toast.makeText(this, "WhatsApp tidak ditemukan", Toast.LENGTH_SHORT).show()
            }
        }

        // -------- MAPS -> GOOGLE MAPS --------
        tvRumah.setOnClickListener {
            val alamat = tvRumah.text.toString()

            val mapsIntent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("geo:0,0?q=${Uri.encode(alamat)}")
                setPackage("com.google.android.apps.maps")
            }

            try {
                startActivity(mapsIntent)
            } catch (e: Exception) {
                Toast.makeText(this, "Google Maps tidak ditemukan", Toast.LENGTH_SHORT).show()
            }
        }

        // -------- GITHUB -> BROWSER / GITHUB APP --------
        tvGithub.setOnClickListener {
            val url = tvGithub.text.toString()

            val githubIntent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(
                    if (url.startsWith("http")) url
                    else "https://$url"
                )
            }

            try {
                startActivity(githubIntent)
            } catch (e: Exception) {
                Toast.makeText(this, "Tidak bisa membuka GitHub", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
