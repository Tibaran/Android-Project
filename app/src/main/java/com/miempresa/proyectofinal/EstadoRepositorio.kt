package com.miempresa.proyectofinal
import com.orm.SugarRecord

class EstadoRepositorio {
    fun crearEstado(){
        var estado = Estado("0","0")
        SugarRecord.save(estado)
    }
    fun count():Long{
        return SugarRecord.count<Estado>(Estado::class.java)
    }

    fun ver():Estado{
        var estado: Estado = SugarRecord.findById(Estado::class.java, 1)
        return estado
    }

    fun actualizar(id_playa:String,id_categoria:String){
        var estado: Estado = SugarRecord.findById(Estado::class.java, 1)
        estado.id_playa = id_playa
        estado.id_categoria = id_categoria
        SugarRecord.save(estado)
    }
    fun actualizarPlaya(id_playa:String){
        var estado: Estado = SugarRecord.findById(Estado::class.java, 1)
        estado.id_playa = id_playa
        SugarRecord.save(estado)
    }
    fun actualizarCategoria(id_categoria:String){
        var estado: Estado = SugarRecord.findById(Estado::class.java, 1)
        estado.id_categoria = id_categoria
        SugarRecord.save(estado)
    }
}