package com.lmamlrg.autenticacionycosulta.data

import android.util.Log
import com.google.gson.Gson
import com.lmamlrg.autenticacionycosulta.model.AccesoAlumno
import com.lmamlrg.autenticacionycosulta.model.DatosAlumno
import com.lmamlrg.autenticacionycosulta.network.AccesoAlumnoApi
import com.lmamlrg.autenticacionycosulta.network.DatosAlumnoApi
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import okhttp3.ResponseBody
import retrofit2.Response
import java.io.IOException

class Repositorio(
    private val accesoAlumnoApi: AccesoAlumnoApi,
    private val datosAlumnoApi: DatosAlumnoApi
) {

    suspend fun getAcceso(matricula: String, contrasenia: String, tipoUsuario: String): String {
        val xml = """
            <soap:Envelope xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:xsd="http://www.w3.org/2001/XMLSchema" xmlns:soap="http://schemas.xmlsoap.org/soap/envelope/">
              <soap:Body>
                <accesoLogin xmlns="http://tempuri.org/">
                  <strMatricula>${matricula}</strMatricula>
                  <strContrasenia>${contrasenia}</strContrasenia>
                  <tipoUsuario>${tipoUsuario}</tipoUsuario>
                </accesoLogin>
              </soap:Body>
            </soap:Envelope>
            """.trimIndent()
        Log.d("xml: ",xml)
        val requestBody = xml.toRequestBody("application/soap+xml".toMediaType())
        return try {
            val response = accesoAlumnoApi.getAcceso(requestBody)
            val responseBodyString = response.string()
            Log.d("SUCCESS", responseBodyString)
            responseBodyString
        } catch (e: IOException){
            Log.e("erroralobtener","${e.message}")
            "Error"
        }
    }

    suspend fun getAlumnoAcademicoWithLineamiento(): String? {
        val xml = "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n" +
                "  <soap:Body>\n" +
                "    <getAlumnoAcademicoWithLineamiento xmlns=\"http://tempuri.org/\" />\n" +
                "  </soap:Body>\n" +
                "</soap:Envelope>".trimIndent()
        val requestBody = xml.toRequestBody("application/soap+xml".toMediaType())
        return try {
            val response = datosAlumnoApi.getAlumnoAcademicoWithLineamiento(requestBody)
                val responseBodyString = response.string()
                Log.d("Logrado:", "$responseBodyString")
            responseBodyString
        } catch (e: Exception) {
            Log.e("nosepudo", "${e.message}")
            ""
        }
    }

}
