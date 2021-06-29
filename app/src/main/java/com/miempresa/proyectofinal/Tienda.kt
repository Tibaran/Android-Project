package com.miempresa.proyectofinal

import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.view.contains
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_listado_edificios.*
import kotlinx.android.synthetic.main.activity_restaurantes.*
import kotlinx.android.synthetic.main.activity_tienda.*
import org.json.JSONException

class Tienda : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tienda)

        MyToolbar().show(this,"Tienda de Alquiler", true)
        val bundle :Bundle?=intent.extras
        if(bundle!=null){
            cargarVista(bundle.getString("id_edificio").toString())
            cargarLista(bundle.getString("edificio").toString())
        }
    }
    fun cargarVista(id_edificio: String){
        AsyncTask.execute {
            val queue = Volley.newRequestQueue(applicationContext)
            val url = resources.getString(R.string.API_IP)+"buildingsInterest"
            val stringRequest = JsonArrayRequest(url,
                    Response.Listener { response ->
                        try {
                            for (i in 0 until response.length()){
                                val id =
                                        response.getJSONObject(i).getString("id")
                                val nombre =
                                        response.getJSONObject(i).getString("nombreEI")
                                val descripcion =
                                        response.getJSONObject(i).getString("descripcion")
                                if(id==id_edificio){
                                    txtNTienda.setText(nombre)
                                    txtNDescri.setText(descripcion)
                                    break
                                }
                            }
                        } catch (e: JSONException) {
                            Toast.makeText(
                                    applicationContext,
                                    "Error al obtener los datos",
                                    Toast.LENGTH_LONG
                            ).show()
                        }
                    }, Response.ErrorListener { response->
                Toast.makeText(
                        applicationContext,
                        "Verifica tu conexion a internet info",
                        Toast.LENGTH_LONG
                ).show()
            })
            queue.add(stringRequest)
        }
    }

    fun cargarLista(n_edificio: String) {
        productos.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        productos.layoutManager = LinearLayoutManager(this)
        var llenarLista = ArrayList<ElementosCVObjeto>()
        AsyncTask.execute {
            val queue = Volley.newRequestQueue(applicationContext)
            val url = resources.getString(R.string.API_IP)+"objects"
            val stringRequest = JsonArrayRequest(url,
                    Response.Listener { response ->
                        try {
                            for (i in 0 until response.length()) {
                                val id =
                                        response.getJSONObject(i).getString("id")
                                val nombre =
                                        response.getJSONObject(i).getString("nombre")
                                val nombreEI =
                                        response.getJSONObject(i).getString("nombreEI")
                                val cantidad =
                                        response.getJSONObject(i).getString("cantidad")
                                val alquilados =
                                        response.getJSONObject(i).getString("alquilados")
                                val urlimagen =
                                        response.getJSONObject(i).getString("imageObj")

                                val capacidad = alquilados+"/"+cantidad
                                if(nombreEI==n_edificio){
                                    llenarLista.add(ElementosCVObjeto(id,nombre,capacidad, urlimagen))

                                }
                            }
                            val adapter = AdaptadorElementosCVObjeto(llenarLista)
                            productos.adapter = adapter
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