package com.lmamlrg.autenticacionycosulta.network
import SoapEnvelope
import retrofit2.Call
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Body

interface SicenetAccessApiService {
    @Headers(
        "Content-Type: text/xml; charset=utf-8",
        "SOAPAction: http://tempuri.org/accessLogin"
    )
    @POST("/ws/wsalumnos.asmx")
    fun sendSoapRequest(@Body soapEnvelope: SoapEnvelope): Call<Void>

}