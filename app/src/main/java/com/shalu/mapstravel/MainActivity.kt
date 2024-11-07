package com.shalu.mapstravel

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var btnwis : Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        btnwis = findViewById(R.id.btnwis)
        btnwis.setOnClickListener(){
            val intent = Intent(this, ListMapsActivity::class.java)
            startActivity(intent)
        }

        Handler(Looper.getMainLooper()).postDelayed({
            //pindah ke page berikutnya
            val intent = Intent(this, ListMapsActivity::class.java)
            startActivity(intent)
            finish()  //kill the activity
        }, 3000)


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}