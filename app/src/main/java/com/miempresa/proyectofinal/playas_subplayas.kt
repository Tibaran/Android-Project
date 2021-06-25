package com.miempresa.proyectofinal

import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_playas_lugar.*
import kotlinx.android.synthetic.main.activity_playas_subplayas.*
import org.json.JSONException

class playas_subplayas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playas_subplayas)

        MyToolbar().show(this,"SubPlayas", true)
        val bundle :Bundle?=intent.extras
        if(bundle!=null){
            cargarLista(bundle.getString("id").toString())
        }

    }
    fun cargarLista(playa_id: String) {
        listaSub_Playas.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        listaSub_Playas.layoutManager = LinearLayoutManager(this)
        var llenarLista = ArrayList<ElementosCVSubPlaya>()
        AsyncTask.execute {
            val queue = Volley.newRequestQueue(applicationContext)
            val url = resources.getString(R.string.API_IP)+"/subplayas"
            val stringRequest = JsonArrayRequest(url,
                Response.Listener { response ->
                    try {
                        for (i in 0 until response.length()) {
                            val id =
                                response.getJSONObject(i).getString("id")
                            val id_playa =
                                response.getJSONObject(i).getString("playa")
                            val nombre =
                                response.getJSONObject(i).getString("nombre")
                            if (id_playa.equals(playa_id)) {
                                llenarLista.add(
                                    ElementosCVSubPlaya(
                                        id,
                                        id_playa,
                                        nombre,
                                        BitmapFactory.decodeResource(resources, R.drawable.playa)
                                    )
                                )
                            }
                        }
                        val adapter = AdaptadorElementosCVSubPlaya(llenarLista)
                        listaSub_Playas.adapter = adapter
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
                        response.message,
                        Toast.LENGTH_LONG
                    ).show()
                })
            queue.add(stringRequest)
        }
    }
}