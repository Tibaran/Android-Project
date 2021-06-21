package com.miempresa.proyectofinal

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorElementosCVLugar(val ListaElementos:ArrayList<ElementosCVLugar>): RecyclerView.Adapter<AdaptadorElementosCVLugar.ViewHolder>() {
    override fun getItemCount(): Int {
        return ListaElementos.size;
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val fImagen= itemView.findViewById<ImageView>(R.id.imgLugar);
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.fImagen?.setImageBitmap(ListaElementos[position].imagen)
        //holder?.fImagen?.setImageDrawable(ListaElementos[position].imagen)
        if(ListaElementos[position].estado==1){
            holder?.fImagen?.setBackgroundResource(R.color.verde)
        }else{
            holder?.fImagen?.setBackgroundResource(R.color.rojo)
        }


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.elementos_lista_lugares,parent,false);
        return ViewHolder(v);
    }
}