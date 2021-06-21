package com.miempresa.proyectofinal

import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_descripcion_playa.*
import kotlinx.android.synthetic.main.activity_playas_subplayas.*

class descripcionPlaya : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_descripcion_playa)

        MyToolbar().show(this,"Descripcion Playa", true)
        listaLugares.layoutManager = GridLayoutManager(this,4)
        var llenarLista = ArrayList<ElementosCVLugar>()
        for (i in 1 until 5){
            llenarLista.add(ElementosCVLugar(1,
                    BitmapFactory.decodeResource(resources, R.drawable.sombrilla)))
        }
        for (i in 1 until 5){
            llenarLista.add(ElementosCVLugar(0,
                    BitmapFactory.decodeResource(resources, R.drawable.sombrilla)))
        }

        val adapter = AdaptadorElementosCVLugar(llenarLista)
        listaLugares.adapter = adapter
    }
}