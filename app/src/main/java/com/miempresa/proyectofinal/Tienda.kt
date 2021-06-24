package com.miempresa.proyectofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Tienda : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tienda)

        MyToolbar().show(this,"Tienda de Alquiler", true)
    }
}