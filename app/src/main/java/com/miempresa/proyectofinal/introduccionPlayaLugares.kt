package com.miempresa.proyectofinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_introduccion_playa.*
import kotlinx.android.synthetic.main.activity_main.*

class introduccionPlayaLugares : AppCompatActivity() {
    var id_playa: String?=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_introduccion_playa)
        MyToolbar().show(this,"Lugares", true)
        val bundle :Bundle?=intent.extras
        if(bundle!=null){
            id_playa = bundle.getString("id").toString()
        }
        if(id_playa.isNullOrEmpty()){
            var estadorepo = EstadoRepositorio()
            var estado:Estado = estadorepo.ver()
            id_playa = estado.id_playa
        }
        var estadorepo = EstadoRepositorio()

        btnRestaurantes.setOnClickListener(){
            val intent = Intent(this, listado_edificios::class.java)
            intent.putExtra("id_playa", id_playa)
            intent.putExtra("id_tipoEdificio", "Restaurante")
            estadorepo.actualizarCategoria("Restaurante")
            startActivity(intent)
        }

        btnBar.setOnClickListener(){
            val intent = Intent(this, listado_edificios::class.java)
            intent.putExtra("id_playa", id_playa)
            intent.putExtra("id_tipoEdificio", "2")
            estadorepo.actualizarCategoria("2")
            startActivity(intent)
        }
        btnTienda.setOnClickListener(){
            val intent = Intent(this, listado_edificios::class.java)
            intent.putExtra("id_playa", id_playa)
            intent.putExtra("id_tipoEdificio", "3")
            estadorepo.actualizarCategoria("3")
            startActivity(intent)
        }
        btnPolicia.setOnClickListener(){
            val intent = Intent(this, listado_edificios::class.java)
            intent.putExtra("id_playa", id_playa)
            intent.putExtra("id_tipoEdificio", "4")
            estadorepo.actualizarCategoria("4")
            startActivity(intent)
        }
        btnSalva.setOnClickListener(){
            val intent = Intent(this, listado_edificios::class.java)
            intent.putExtra("id_playa", id_playa)
            intent.putExtra("id_tipoEdificio", "5")
            estadorepo.actualizarCategoria("5")
            startActivity(intent)
        }
    }
}