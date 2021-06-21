package com.miempresa.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import android.widget.Toolbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MyToolbar().show(this,"Inicio", false)
        btnplayas2.setOnClickListener {
            val actividad = Intent(applicationContext, playas_lugar::class.java)
            startActivity(actividad)
        }

        btnlugares.setOnClickListener {
            val actividad = Intent(applicationContext, introduccionPlayaLugares::class.java)
            startActivity(actividad)
        }
    }
//===========================================================
//Menu Contextual
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menucontextual, menu)
        return super.onCreateOptionsMenu(menu)
    }
//Opciones del menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.inicio)
            Toast.makeText(this,"Opcion1Pre", Toast.LENGTH_LONG).show()
        return super.onOptionsItemSelected(item)
    }
//===========================================================
}