package com.miempresa.proyectofinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class Mapa : AppCompatActivity(), OnMapReadyCallback {
    private var ubicacion = ""
    var coordenada = LatLng(0.0, 0.0)
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mapa)
        MyToolbar().show(this,"Mapa", true)
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        val recibidos = this.intent.extras
        if (recibidos != null) {
            ubicacion = intent.extras!!.getString("ubicacion")!!
            var coordenadas:List<String> = ubicacion.split(", ")
            coordenada = LatLng(coordenadas[0].toDouble(),coordenadas[1].toDouble())
        }
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        mMap.getUiSettings().setAllGesturesEnabled(true)
        mMap.getUiSettings().setZoomControlsEnabled(true)
        mMap.getUiSettings().setCompassEnabled(true)
        // Add a marker in Sydney and move the camera
        val playa = coordenada
        mMap.addMarker(MarkerOptions().position(playa).title("Marcador"))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(playa,15f))
    }
}