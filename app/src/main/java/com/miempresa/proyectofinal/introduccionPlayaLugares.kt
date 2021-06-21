package com.miempresa.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_introduccion_playa.*
import kotlinx.android.synthetic.main.activity_main.*

class introduccionPlayaLugares : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduccion_playa)
        MyToolbar().show(this,"Inicio", false)
    }
}