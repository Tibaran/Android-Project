package com.miempresa.proyectofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Restaurantes : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurantes)

        MyToolbar().show(this,"Restaurante", true)
    }
}