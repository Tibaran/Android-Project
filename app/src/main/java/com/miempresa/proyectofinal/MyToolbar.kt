package com.miempresa.proyectofinal

import android.icu.text.CaseMap
import androidx.appcompat.app.AppCompatActivity

class MyToolbar {
    fun show(activities: AppCompatActivity, title: String, upButton:Boolean){
        activities.setSupportActionBar(activities.findViewById(R.id.toolbar))
        activities.supportActionBar?.title = title
        activities.supportActionBar?.setDisplayHomeAsUpEnabled(upButton)
    }
}