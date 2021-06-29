package com.miempresa.proyectofinal

import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_listado_edificios.*
import kotlinx.android.synthetic.main.activity_playas_lugar.*
import org.json.JSONException

class listado_edificios : AppCompatActivity() {
    var id_playa: String?=""
    var id_tipoEdificio: String?=""
    private var listEdificio = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listado_edificios)
        MyToolbar().show(this,"Edificios", true)
        val bundle :Bundle?=intent.extras
        if(bundle!=null){
            id_playa = bundle.getString("id_playa").toString()
            id_tipoEdificio = bundle.getString("id_tipoEdificio").toString()
            rellenarListaEdificio(id_playa.toString())
            cargarLista(id_tipoEdificio.toString())
        }
        if(id_playa.isNullOrEmpty()){
            var estadorepo = EstadoRepositorio()
            var estado:Estado = estadorepo.ver()
            id_playa = estado.id_playa
            id_tipoEdificio = estado.id_categoria
            rellenarListaEdificio(id_playa.toString())
            cargarLista(id_tipoEdificio.toString())
        }

        //Recoger los datos de la api, tenemos que buscar el id_edificio
    }
    fun rellenarListaEdificio(id_playa: String) {
        AsyncTask.execute {
            val queue = Volley.newRequestQueue(applicationContext)
            val url = resources.getString(R.string.API_IP)+"beachBuilding"
            val stringRequest = JsonArrayRequest(url,
                    Response.Listener { response ->
                        try {
                            for (i in 0 until response.length()) {
                                val playa_id =
                                        response.getJSONObject(i).getString("nombre")
                                val edificio_id =
                                        response.getJSONObject(i).getString("nombreEI")
                                if (playa_id == id_playa){
                                    listEdificio.add(edificio_id)
                                }
                            }
                        } catch (e: JSONException) {
                            Toast.makeText(
                                    applicationContext,
                                    "Error al obtener los datos",
                                    Toast.LENGTH_LONG
                            ).show()
                        }
                    }, Response.ErrorListener {
                Toast.makeText(
                        applicationContext,
                        "Revise su conexion a  LS",
                        Toast.LENGTH_LONG
                ).show()
            })
            queue.add(stringRequest)
        }
    }

    fun cargarLista(id_tipoEdificio: String) {
        listaEdificios.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        listaEdificios.layoutManager = LinearLayoutManager(this)
        var llenarLista = ArrayList<ElementosCVEdificio>()
        AsyncTask.execute {
            val queue = Volley.newRequestQueue(applicationContext)
            val url = resources.getString(R.string.API_IP)+"buildingsInterest"
            val stringRequest = JsonArrayRequest(url,
                    Response.Listener { response ->
                        try {
                            for (i in 0 until response.length()) {
                                val id =
                                        response.getJSONObject(i).getString("id")
                                val nombre =
                                        response.getJSONObject(i).getString("nombreEI")
                                val tipo =
                                    response.getJSONObject(i).getString("tipoEdificio")
                                if(listEdificio.contains(nombre)){
                                    if(tipo == id_tipoEdificio){
                                        llenarLista.add(ElementosCVEdificio(id,nombre,BitmapFactory.decodeResource(resources, R.drawable.restaurante)))
                                    }
                                }
                            }
                            val adapter = AdaptadorElementosCVEdificio(llenarLista)
                            listaEdificios.adapter = adapter
                        } catch (e: JSONException) {
                            Toast.makeText(
                                    applicationContext,
                                    "Error al obtener los datos",
                                    Toast.LENGTH_LONG
                            ).show()
                        }
                    }, Response.ErrorListener {
                Toast.makeText(
                        applicationContext,
                        "Revise su conexion a internet EDI",
                        Toast.LENGTH_LONG
                ).show()
            })
            queue.add(stringRequest)
        }
    }
}