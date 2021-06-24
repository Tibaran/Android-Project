package com.miempresa.proyectofinal

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class AdaptadorElementosCVPlaya(val ListaElementos:ArrayList<ElementosCVPlaya>): RecyclerView.Adapter<AdaptadorElementosCVPlaya.ViewHolder>(){
    override fun getItemCount(): Int {
        return ListaElementos.size;
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val fTitulo = itemView.findViewById<TextView>(R.id.lblTitulo)
        val fImagen= itemView.findViewById<ImageView>(R.id.imgCardView);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder?.fTitulo?.text=ListaElementos[position].titulo
        holder?.fImagen?.setImageBitmap(ListaElementos[position].imagen)
        val id = ListaElementos[position].id

        var button = holder.itemView.findViewById<Button>(R.id.btnVer)
        var button2 = holder.itemView.findViewById<Button>(R.id.btnLugar)
        button.setOnClickListener(){
            Toast.makeText(holder.itemView.context, "Pulsaste boton dentro de cardView "+holder?.fTitulo.text, Toast.LENGTH_LONG).show()
            val llamaractividad = Intent(holder.itemView.context, playas_subplayas::class.java)
            llamaractividad.putExtra("id", id.toString())
            holder.itemView.context.startActivity(llamaractividad)
        }
        button2.setOnClickListener(){
            val llamaractividad = Intent(holder.itemView.context, introduccionPlayaLugares::class.java)
            holder.itemView.context.startActivity(llamaractividad)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.elementos_lista_playa,parent,false);
        return ViewHolder(v);
    }
}