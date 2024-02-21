package com.lmamlrg.autenticacionycosulta.data

import android.util.Log
import com.lmamlrg.autenticacionycosulta.model.AccesoAlumnoEnvelope
import com.lmamlrg.autenticacionycosulta.model.DatosAlumnoEnvelope
import com.lmamlrg.autenticacionycosulta.network.AccesoAlumnoApi
import com.lmamlrg.autenticacionycosulta.network.DatosAlumnoApi
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.simpleframework.xml.core.Persister
import java.io.IOException
import java.io.StringReader

private const val TAG_SUCCESS = "SUCCESS"
private const val TAG_ERROR = "ERROR"
class Repositorio(
    private val accesoAlumnoApi: AccesoAlumnoApi,
    private val datosAlumnoApi: DatosAlumnoApi
) {

    suspend fun getAcceso(matricula: String, contrasenia: String, tipoUsuario: String): String? {
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

            val serializer = Persister()
            val reader = StringReader(responseBodyString)
            val envelope = serializer.read(AccesoAlumnoEnvelope::class.java, reader)
            var respuestaJson = envelope.body?.response?.result.toString()

            // Utiliza Gson para convertir el JSON a un objeto Kotlin
            Log.d(TAG_SUCCESS, respuestaJson)
            respuestaJson
        } catch (e: IOException){
            Log.e(TAG_ERROR,"${e.message}")
            ""
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
            val serializer = Persister()
            val reader = StringReader(responseBodyString)
            val envelope = serializer.read(DatosAlumnoEnvelope::class.java, reader)

            val jsonString = envelope.body?.response?.result.toString()
            jsonString

        } catch (e: IOException) {
            Log.e(TAG_ERROR, "${e.message}")
            ""
        }
    }

}
