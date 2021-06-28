package com.miempresa.proyectofinal

import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_playas_lugar.*
import org.json.JSONException

class playas_lugar : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playas_lugar)

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)
        MyToolbar().show(this,"Playas Lugar", true)
        cargarLista()

    }
    fun cargarLista() {
        listaPlayas.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        listaPlayas.layoutManager = LinearLayoutManager(this)
        var llenarLista = ArrayList<ElementosCVPlaya>()
        AsyncTask.execute {
            val queue = Volley.newRequestQueue(applicationContext)
            val url = resources.getString(R.string.API_IP)+"beaches"
            val stringRequest = JsonArrayRequest(url,
                Response.Listener { response ->
                    try {
                        for (i in 0 until response.length()) {
                            val id =
                                response.getJSONObject(i).getString("id")
                            val nombre =
                                response.getJSONObject(i).getString("nombre")
                            val ubicacion =
                                response.getJSONObject(i).getString("ubicacion")
                            val imagenurl =
                                    response.getJSONObject(i).getString("imagePlay")
                            llenarLista.add(ElementosCVPlaya(id,nombre,ubicacion, imagenurl))
                        }
                        val adapter = AdaptadorElementosCVPlaya(llenarLista)
                        listaPlayas.adapter = adapter
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
                        "Revise su conexion a internet",
                        Toast.LENGTH_LONG
                    ).show()
                })
            queue.add(stringRequest)
        }
    }
}