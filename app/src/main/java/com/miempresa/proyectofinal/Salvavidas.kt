package com.miempresa.proyectofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Salvavidas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salvavidas)

        MyToolbar().show(this,"Salvavidas", true)
    }
}