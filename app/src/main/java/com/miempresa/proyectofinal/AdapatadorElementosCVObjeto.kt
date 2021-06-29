package com.miempresa.proyectofinal

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class AdaptadorElementosCVObjeto(val ListaElementos:ArrayList<ElementosCVObjeto>): RecyclerView.Adapter<AdaptadorElementosCVObjeto.ViewHolder>(){

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val fName = itemView.findViewById<TextView>(R.id.lblObName)
        val fCantidad= itemView.findViewById<TextView>(R.id.lblObCantidad);
    }
    override fun onBindViewHolder(holder: AdaptadorElementosCVObjeto.ViewHolder, position: Int) {
        holder?.fName?.text=ListaElementos[position].nombre
        holder?.fCantidad?.text=ListaElementos[position].capacidad
        var imagen = holder.itemView.findViewById<ImageView>(R.id.imgobjeto)
        val url = ListaElementos[position].imagen
        Picasso.get().load(url).into(imagen)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorElementosCVObjeto.ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.objetos_tienda,parent,false);
        return AdaptadorElementosCVObjeto.ViewHolder(v);
    }

    override fun getItemCount(): Int {
        return ListaElementos.size;
    }


}