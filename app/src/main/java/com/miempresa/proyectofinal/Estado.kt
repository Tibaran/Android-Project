package com.miempresa.proyectofinal

import com.orm.dsl.Table

@Table
data class Estado(
    var id: Long? = null,
    var id_playa: String? = null,
    var id_categoria: String? = null){
    constructor(id_playa:String?,id_categoria:String?):this(){
        this.id_playa = id_playa
        this.id_categoria = id_categoria
    }
}