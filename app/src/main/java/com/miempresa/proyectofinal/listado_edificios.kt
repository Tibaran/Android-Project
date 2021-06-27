package com.miempresa.proyectofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class listado_edificios : AppCompatActivity() {
    var id_playa: String?=""
    var id_tipoEdificio: String?=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_edificios)
        MyToolbar().show(this,"Edificios", true)
        val bundle :Bundle?=intent.extras
        if(bundle!=null){
            id_playa = bundle.getString("id_playa").toString()
            id_tipoEdificio = bundle.getString("id_tipoEdificio").toString()
        }
        if(id_playa.isNullOrEmpty()){
            var estadorepo = EstadoRepositorio()
            var estado:Estado = estadorepo.ver()
            id_playa = estado.id_playa
            id_tipoEdificio = estado.id_categoria
        }

        //Recoger los datos de la api, tenemos que buscar el id_edificio
    }
}