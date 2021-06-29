package com.miempresa.proyectofinal

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import kotlinx.android.synthetic.main.activity_restaurantes.*
import org.json.JSONException

class Restaurantes : AppCompatActivity() {
    private var nom :String?=""
    private var desc :String?=""
    private var cap :String?=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurantes)
        MyToolbar().show(this,"Restaurante", true)
        val bundle :Bundle?=intent.extras
        if(bundle!=null){
            Toast.makeText(this,bundle.getString("id_edificio").toString(), Toast.LENGTH_LONG).show()
            cargarVista(bundle.getString("id_edificio").toString(),bundle.getString("edificio").toString() )
            cargarCapacidad(bundle.getString("edificio").toString())
        }
        txtNombre.setText(nom)
        txtDescripcion.setText(desc)
        txtCapacidad.setText(cap)

    }
    fun cargarVista(id_edificio: String, n_edificio: String){
        AsyncTask.execute {
            val queue = Volley.newRequestQueue(applicationContext)
            val url = resources.getString(R.string.API_IP)+"buildingsInterest/"+id_edificio+"/"
            val stringRequest = JsonArrayRequest(url,
                Response.Listener { response ->
                    try {

                            val nombre =
                                response.getJSONObject(0).getString("nombreEI")
                            val descripcion =
                                response.getJSONObject(0).getString("descripcion")
                            val direccion =
                                response.getJSONObject(0).getString("direccion")
                            val contacto =
                                response.getJSONObject(0).getString("contacto")

                                nom = nombre
                                desc = descripcion


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
                                cap = estado + "/" + capacidad
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