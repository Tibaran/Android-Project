package com.miempresa.proyectofinal

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_bares.*
import kotlinx.android.synthetic.main.activity_bares.txtCapacidad
import kotlinx.android.synthetic.main.activity_bares.txtDescripcion
import kotlinx.android.synthetic.main.activity_bares.txtNombre
import kotlinx.android.synthetic.main.activity_restaurantes.*
import org.json.JSONException

class Bares : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bares)
        MyToolbar().show(this,"Bar", true)
        val bundle :Bundle?=intent.extras
        if(bundle!=null){
            cargarVista(bundle.getString("id_edificio").toString())
            cargarCapacidad(bundle.getString("edificio").toString())
        }
    }
    fun cargarVista(id_edificio: String){
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
                            val descripcion =
                                response.getJSONObject(i).getString("descripcion")
                            val direccion =
                                response.getJSONObject(i).getString("direccion")
                            val contacto =
                                response.getJSONObject(i).getString("contacto")
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
                }, Response.ErrorListener {
                    Toast.makeText(
                        applicationContext,
                        "Revise su conexion a internet Info",
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