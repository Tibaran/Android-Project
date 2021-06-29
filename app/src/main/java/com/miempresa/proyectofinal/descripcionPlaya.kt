package com.miempresa.proyectofinal

import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_descripcion_playa.*
import kotlinx.android.synthetic.main.activity_playas_subplayas.*
import org.json.JSONException

class descripcionPlaya : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_descripcion_playa)

        MyToolbar().show(this,"Descripcion Playa", true)
        val bundle :Bundle?=intent.extras
        if(bundle!=null){
            cargarLista(bundle.getString("id_playa").toString())
        }
    }
    fun cargarLista(subplaya_id: String) {
        listaLugares.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        listaLugares.layoutManager = GridLayoutManager(this,4)
        var llenarLista = ArrayList<ElementosCVLugar>()
        AsyncTask.execute {
            val queue = Volley.newRequestQueue(applicationContext)
            val url = resources.getString(R.string.API_IP)+"umbrellas"
            val stringRequest = JsonArrayRequest(url,
                Response.Listener { response ->
                    try {
                        for (i in 0 until response.length()) {
                            val id =
                                response.getJSONObject(i).getString("subPlaya")
                            val estado =
                                response.getJSONObject(i).getString("estado")
                            if (id.equals(subplaya_id)) {
                                llenarLista.add(
                                    ElementosCVLugar(
                                        estado,
                                        BitmapFactory.decodeResource(resources, R.drawable.sombrilla)
                                    )
                                )
                            }
                        }
                        val adapter = AdaptadorElementosCVLugar(llenarLista)
                        listaLugares.adapter = adapter
                    } catch (e: JSONException) {
                        Toast.makeText(
                            applicationContext,
                            "Error al obtener los datos",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }, Response.ErrorListener { response ->
                    Toast.makeText(
                        applicationContext,
                        "Verifique su conexion a internet",
                        Toast.LENGTH_LONG
                    ).show()
                })
            queue.add(stringRequest)
        }
    }
}