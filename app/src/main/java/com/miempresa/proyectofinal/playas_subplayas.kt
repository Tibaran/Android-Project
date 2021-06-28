package com.miempresa.proyectofinal

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.Volley
import com.google.android.gms.maps.model.LatLng
import com.miempresa.apiclima.WeatherResponse
import com.miempresa.apiclima.weatherapi
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_playas_lugar.*
import kotlinx.android.synthetic.main.activity_playas_subplayas.*
import kotlinx.android.synthetic.main.vista_clima.*
import org.json.JSONException
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class playas_subplayas : AppCompatActivity() {
    var id: String?=""
    val apikey:String= "a2c4e77f9dd147f1a553cdcace346bef"
    private var ReNombre: TextView? = null
    private var Temp: TextView? = null
    private var TemMax: TextView? = null
    private var TemMin: TextView? = null
    private var imagen: ImageView? = null

    lateinit var ubicacion:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_playas_subplayas)

        ReNombre = findViewById(R.id.txtNomRegion)
        Temp = findViewById(R.id.txtTemp)
        TemMax = findViewById(R.id.txtTemMax)
        TemMin = findViewById(R.id.txtTemMin)
        imagen = findViewById(R.id.imgclima)

        MyToolbar().show(this,"SubPlayas", true)
        val bundle :Bundle?=intent.extras
        if(bundle!=null){
            id = bundle.getString("id").toString()
            ubicacion = bundle.getString("ubicacion").toString()
            var coordenadas:List<String> = ubicacion.split(", ")
            getClima(coordenadas[0], coordenadas[1])
            cargarLista(id.toString())
        }
        if(id.isNullOrEmpty()){
            var estadorepo = EstadoRepositorio()
            var estado:Estado = estadorepo.ver()
            id = estado.id_playa
            cargarLista(id.toString())
        }
    }

    fun getClima(lat:String, lon:String){
        val retrofit= Retrofit.Builder()
                .baseUrl("https://api.openweathermap.org/data/2.5/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        val myapi = retrofit.create(weatherapi::class.java)!!
        var example = myapi.getweather(lat, lon, apikey)
        example.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(call: Call<WeatherResponse>, response: retrofit2.Response<WeatherResponse>) {
                if (response.code() == 404) {
                    Toast.makeText(applicationContext, "Ingrese ciudad correcta", Toast.LENGTH_LONG).show()
                }
                else if (!(response.isSuccessful())){
                    Toast.makeText(applicationContext, response.code() , Toast.LENGTH_LONG).show()
                }
                val mydata = response.body()!!

                val tem = ((mydata.main!!.temp)-273.15).toInt()
                val temMax = ((mydata.main!!.temp_max)-273.15).toInt()
                val temMin = ((mydata.main!!.temp_min)-273.15).toInt()
                val ciudad = mydata.name!!


                ReNombre!!.text = ciudad
                Temp!!.text = tem.toString()+"°C"
                TemMax!!.text = temMax.toString()+"°C"
                TemMin!!.text = temMin.toString()+"°C"

                var icon2 = mydata.weather!!.get(0).icon

                val imgurl = "https://openweathermap.org/img/wn/"+icon2+"@2x.png"

                Picasso.get().load(imgurl).into(imagen)

            }
            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Toast.makeText(applicationContext, t.message , Toast.LENGTH_LONG).show()
            }
        })
    }

    fun cargarLista(playa_id: String) {
        listaSub_Playas.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        listaSub_Playas.layoutManager = LinearLayoutManager(this)
        var llenarLista = ArrayList<ElementosCVSubPlaya>()
        AsyncTask.execute {
            val queue = Volley.newRequestQueue(applicationContext)
            val url = resources.getString(R.string.API_IP)+"subbeaches"
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
                        "Verifique su conexion de internet",
                        Toast.LENGTH_LONG
                    ).show()
                })
            queue.add(stringRequest)
        }
    }
}