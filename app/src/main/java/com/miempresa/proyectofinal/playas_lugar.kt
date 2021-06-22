package com.miempresa.proyectofinal

import android.content.Intent
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.activity_playas_lugar.*

class playas_lugar : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playas_lugar)

        MyToolbar().show(this,"Playas Lugar", true)
        listaPlayas.layoutManager = GridLayoutManager(this,2)
        var llenarLista = ArrayList<ElementosCVPlaya>()
        for (i in 1 until 8){
            llenarLista.add(ElementosCVPlaya(i.toString(),"Titulo "+i,
                    BitmapFactory.decodeResource(resources, R.drawable.playa)))
        }

        val adapter = AdaptadorElementosCVPlaya(llenarLista)
        listaPlayas.adapter = adapter

    }
}