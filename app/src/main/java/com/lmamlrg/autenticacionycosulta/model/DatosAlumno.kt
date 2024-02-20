package com.lmamlrg.autenticacionycosulta.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName
import java.util.Date

@Serializable
data class DatosAlumno (
    @SerialName("fechaReins")
    var fechaReins: Date,
    @SerialName("modEducativo")
    var modEducativo: Int,
    @SerialName("adeudo")
    var adeudo: Boolean,
    @SerialName("urlFoto")
    var urlFoto: String,
    @SerialName("adeudoDescripcion")
    var adeudoDescripcion: String,
    @SerialName("inscrito")
    var inscrito: Boolean,
    @SerialName("estatus")
    var estatus: String,
    @SerialName("semActual")
    var semActual: Int,
    @SerialName("cdtosAcumulados")
    var cdtosAcumulados: Int,
    @SerialName("cdtosActuales")
    var cdtosActuales: Int,
    @SerialName("especialidad")
    var especialidad: String,
    @SerialName("carrera")
    var carrera: String,
    @SerialName("lineamiento")
    var lineamiento: Int,
    @SerialName("nombre")
    var nombre: String,
    @SerialName("matricula")
    var matricula: String
)
