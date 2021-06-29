package com.miempresa.proyectofinal

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class AdaptadorElementosCVPlaya(val ListaElementos:ArrayList<ElementosCVPlaya>): RecyclerView.Adapter<AdaptadorElementosCVPlaya.ViewHolder>(){
    override fun getItemCount(): Int {
        return ListaElementos.size;
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val fTitulo = itemView.findViewById<TextView>(R.id.lblTitulo)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder?.fTitulo?.text=ListaElementos[position].titulo
        var imagen = holder.itemView.findViewById<ImageView>(R.id.imgCardView)


        val url = ListaElementos[position].imagen
        Picasso.get().load(url).into(imagen)
        val ubicacion = ListaElementos[position].ubicacion
        val id = ListaElementos[position].titulo

        var button = holder.itemView.findViewById<Button>(R.id.btnSubPlaya)
        var button2 = holder.itemView.findViewById<Button>(R.id.btnEdificios)
        var button3 = holder.itemView.findViewById<ImageButton>(R.id.btnUbicacion)
        button.setOnClickListener(){
            var estadorepo = EstadoRepositorio()
            estadorepo.actualizarPlaya(id)
            val llamaractividad = Intent(holder.itemView.context, playas_subplayas::class.java)
            llamaractividad.putExtra("id", id.toString())
            llamaractividad.putExtra("ubicacion",ubicacion)
            holder.itemView.context.startActivity(llamaractividad)
        }
        button2.setOnClickListener(){
            var estadorepo = EstadoRepositorio()
            estadorepo.actualizarPlaya(id)
            val llamaractividad = Intent(holder.itemView.context, introduccionPlayaLugares::class.java)
            holder.itemView.context.startActivity(llamaractividad)
        }
        button3.setOnClickListener(){
            val llamaractividad = Intent(holder.itemView.context, Mapa::class.java)
            llamaractividad.putExtra("ubicacion",ubicacion)
            holder.itemView.context.startActivity(llamaractividad)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent?.context).inflate(R.layout.elementos_lista_playa,parent,false);
        return ViewHolder(v);
    }
}