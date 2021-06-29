package com.miempresa.proyectofinal

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorElementosCVEdificio(val ListaElementos:ArrayList<ElementosCVEdificio>): RecyclerView.Adapter<AdaptadorElementosCVEdificio.ViewHolder>(){
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
        val id_edificio = ListaElementos[position].id
        val edificio = ListaElementos[position].titulo
        var estadorepo = EstadoRepositorio()
        var estado = estadorepo.ver()

        var button = holder.itemView.findViewById<Button>(R.id.btnInfo)
        button.setOnClickListener(){
            val llamaractividad = Intent(holder.itemView.context,
                when(estado.id_categoria){
                    "Restaurante"->Restaurantes::class.java
                    "Bares"->Bares::class.java
                    "Tienda"->Tienda::class.java
                    "Policia"->Policia::class.java
                    "Salvavidas"->Salvavidas::class.java
                    else->listado_edificios::class.java
                }
            )
            llamaractividad.putExtra("id_edificio", id_edificio.toString())
            llamaractividad.putExtra("edificio", edificio.toString())
            holder.itemView.context.startActivity(llamaractividad)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.elementos_lista_edificios,parent,false);
        return ViewHolder(v);
    }
}