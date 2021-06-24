package com.miempresa.proyectofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Bares : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bares)

        MyToolbar().show(this,"Bar", true)
    }
}