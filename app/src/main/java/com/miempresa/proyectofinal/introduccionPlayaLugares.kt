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
        MyToolbar().show(this,"Lugares", true)

        btnRestaurantes.setOnClickListener(){
            val intent = Intent(this, Restaurantes::class.java)
            startActivity(intent)
        }

        btnBar.setOnClickListener(){
            val intent = Intent(this, Bares::class.java)
            startActivity(intent)
        }
        btnTienda.setOnClickListener(){
            val intent = Intent(this, Tienda::class.java)
            startActivity(intent)
        }
        btnPolicia.setOnClickListener(){
            val intent = Intent(this, Policia::class.java)
            startActivity(intent)
        }
        btnSalva.setOnClickListener(){
            val intent = Intent(this, Salvavidas::class.java)
            startActivity(intent)
        }
    }
}