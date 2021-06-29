package com.miempresa.proyectofinal

import android.content.Intent
import android.net.Uri
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_restaurantes.*
import org.json.JSONException

class Restaurantes : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurantes)
        MyToolbar().show(this,"Restaurante", true)
        val bundle :Bundle?=intent.extras
        if(bundle!=null){
            cargarVista(bundle.getString("id_edificio").toString())
            cargarCapacidad(bundle.getString("edificio").toString())
        }
        val mensaje= "Buen dia me podria brindar informacion sobre su edificio"

        btnContactar.setOnClickListener(){
            val intent = Intent()
            intent.setAction(Intent.ACTION_VIEW)
            var uri = "whatsapp://send?phone="+51977480441+"&text="+mensaje
            intent.setData(Uri.parse(uri))
            startActivity(intent)
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
                                txtNombre.setText(nombre)
                                txtDescripcion.setText(descripcion)
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
                }, Response.ErrorListener {response->
                    Toast.makeText(
                        applicationContext,
                        "Verifica tu conexion a internet info",
                        Toast.LENGTH_LONG
                    ).show()
                })
            queue.add(stringRequest)
        }
    }
    fun cargarCapacidad(n_edificio:String){
        AsyncTask.execute {
            val queue = Volley.newRequestQueue(applicationContext)
            val url = resources.getString(R.string.API_IP)+"foodDirnksBuildings"
            val stringRequest = JsonArrayRequest(url,
                Response.Listener { response ->
                    try {
                        for (i in 0 until response.length()) {
                            val capacidad =
                                response.getJSONObject(i).getString("capacidad")
                            val estado =
                                response.getJSONObject(i).getString("estadoCapacidad")
                            val edificio =
                                response.getJSONObject(i).getString("edificioInteres")
                            if(edificio == n_edificio) {
                                txtCapacidad.setText(estado + "/" + capacidad)
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
                }, Response.ErrorListener {
                    Toast.makeText(
                        applicationContext,
                        "Revise su conexion a internet CAP",
                        Toast.LENGTH_LONG
                    ).show()
                })
            queue.add(stringRequest)
        }
    }
}