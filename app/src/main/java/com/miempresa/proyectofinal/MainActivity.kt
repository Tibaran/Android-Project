package com.miempresa.proyectofinal

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.contain_main.*

class MainActivity : AppCompatActivity() , NavigationView.OnNavigationItemSelectedListener{

    lateinit var toolbar: Toolbar
    lateinit var drawerLayout: DrawerLayout
    lateinit var  navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var estadorepo = EstadoRepositorio()
        if (estadorepo.count()<=0){
            estadorepo.crearEstado()
        }

        val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
        StrictMode.setThreadPolicy(policy)

        Toast.makeText(this, "Bienvenido!!!", Toast.LENGTH_LONG).show()

        toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        drawerLayout = findViewById(R.id.drawer_layout)
        navView = findViewById(R.id.nav_view)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar , 0,0
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        navView.setNavigationItemSelectedListener(this)

        btnplayas2.setOnClickListener {
            val actividad = Intent(applicationContext, playas_lugar::class.java)
            startActivity(actividad)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.inicio -> {
                Toast.makeText(this, "Bienvenido", Toast.LENGTH_SHORT).show()
            }
            R.id.Playas -> {
                val actividad = Intent(applicationContext, playas_lugar::class.java)
                startActivity(actividad)
                finish()
            }
            R.id.regiones -> {
                val actividad = Intent(applicationContext, playas_lugar::class.java)
                startActivity(actividad)
                finish()
            }
            R.id.ayuda -> {
                Toast.makeText(this, "Elegiste Ayuda", Toast.LENGTH_SHORT).show()
            }
            R.id.configuracion -> {
                Toast.makeText(this, "Elegiste Configuraciones", Toast.LENGTH_SHORT).show()
            }
            R.id.salir -> {
                val alert = AlertDialog.Builder(this)
                alert.setTitle("Confirmacion de Salida..!!")
                alert.setIcon(R.drawable.salir)
                alert.setMessage("¿Estas seguro que quieres salir?")
                alert.setCancelable(false)
                alert.setPositiveButton(android.R.string.ok) {
                        dialog, which ->
                    finishAffinity()
                }
                alert.setNegativeButton("Cancelar", null)
                alert.show()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
/*===========================================================
//Menu Contextual
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menucontextual, menu)
        return super.onCreateOptionsMenu(menu)
    }
//Opciones del menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.inicio)
            Toast.makeText(this,"Opcion1Pre", Toast.LENGTH_LONG).show()
        return super.onOptionsItemSelected(item)
    }
//===========================================================*/
}