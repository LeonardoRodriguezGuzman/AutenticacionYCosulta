package com.lmamlrg.autenticacionycosulta.data

import AccesoLogin
import SoapBody
import SoapEnvelope
import retrofit2.Retrofit
import com.lmamlrg.autenticacionycosulta.network.SicenetAccessApiService
import retrofit2.converter.simplexml.SimpleXmlConverterFactory


private const val BASE_URL = "https://sicenet.surguanajuato.tecnm.mx"
@Suppress("DEPRECATION")
class SicenetAccessApiService {

    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .build()

    val service = retrofit.create(SicenetAccessApiService::class.java)

    val body = AccesoLogin("tu_matricula", "tu_contraseña", "ALUMNO") // Reemplaza con tus datos
    val soapBody = SoapBody(body)
    val soapEnvelope = SoapEnvelope(soapBody)

    val call = service.sendSoapRequest(soapEnvelope)




}
