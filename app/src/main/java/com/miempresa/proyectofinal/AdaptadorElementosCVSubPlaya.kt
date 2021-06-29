package com.miempresa.proyectofinal

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class AdaptadorElementosCVSubPlaya(val ListaElementos:ArrayList<ElementosCVSubPlaya>): RecyclerView.Adapter<AdaptadorElementosCVSubPlaya.ViewHolder>(){
    override fun getItemCount(): Int {
        return ListaElementos.size;
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val fTitulo = itemView.findViewById<TextView>(R.id.lblTitulo)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder?.fTitulo?.text=ListaElementos[position].titulo
        var imagen = holder.itemView.findViewById<ImageView>(R.id.imgCardViewSub)
        val url = ListaElementos[position].imagen
        Picasso.get().load(url).into(imagen)
        val id = ListaElementos[position].id
        val id_playa = ListaElementos[position].titulo

        var button = holder.itemView.findViewById<Button>(R.id.btnSubPlaya)
        button.setText("Ver Lugares")
        button.setOnClickListener(){
            val llamaractividad = Intent(holder.itemView.context, descripcionPlaya::class.java)
            llamaractividad.putExtra("id", id.toString())
            llamaractividad.putExtra("id_playa", id_playa.toString())
            holder.itemView.context.startActivity(llamaractividad)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.elementos_lista_subplaya,parent,false);
        return ViewHolder(v);
    }
}