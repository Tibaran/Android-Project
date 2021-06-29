package com.miempresa.proyectofinal

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_salvavidas.*
import org.json.JSONException

class Salvavidas : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_salvavidas)

        MyToolbar().show(this,"Salvavidas", true)

        val bundle :Bundle?=intent.extras
        if(bundle!=null){
            cargarVista(bundle.getString("id_edificio").toString())
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
                                val direccion =
                                        response.getJSONObject(i).getString("direccion")
                                val contacto =
                                        response.getJSONObject(i).getString("contacto")
                                if(id==id_edificio){
                                    txtNomEdi.setText(nombre)
                                    txtInfor.setText(descripcion)
                                    txtUbi.setText(direccion)
                                    txtCelu.setText(contacto)
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
}