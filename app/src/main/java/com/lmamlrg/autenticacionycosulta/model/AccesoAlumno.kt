package com.lmamlrg.autenticacionycosulta.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccesoAlumno (
    @SerialName("acceso")
    val acceso : Boolean,
    @SerialName("estatus")
    val estatus : String,
    @SerialName("tipoUsuario")
    val tipoUsuario : Int,
    @SerialName("contrasenia")
    val contrasenia : String,
    @SerialName("matricula")
    val matricula : String
){
    override fun toString() : String{
        return ("estatus: $estatus, matricula: $matricula" )
    }
}


