@file:Suppress("DEPRECATION")

package com.lmamlrg.autenticacionycosulta

import AccesoLogin
import SoapBody
import SoapEnvelope
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.lmamlrg.autenticacionycosulta.network.SicenetAccessApiService
import com.lmamlrg.autenticacionycosulta.ui.theme.AutenticacionYCosultaTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AutenticacionYCosultaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AutenticacionYCosultaTheme {
        Greeting("Android")
    }
}

@Composable
fun FilledTonalButtonE(onClick: () -> Unit) {
    FilledTonalButton(onClick = {
    }) {

    }
}

    @Preview
    @Composable
    fun PreviewBoton() {
        Column{
            FilledTonalButtonE(onClick = {
                comprobar()
            })
        }
    }

fun comprobar(){
    val retrofit = Retrofit.Builder()
        .baseUrl("http://sicenet.surguanajuato.tecnm.mx")
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .build()

    val service = retrofit.create(SicenetAccessApiService::class.java)

    val body = AccesoLogin("", "", "ALUMNO")
    val soapBody = SoapBody(body)
    val soapEnvelope = SoapEnvelope(soapBody)

    val call = service.sendSoapRequest(soapEnvelope)
    call.enqueue(object : Callback<Void> {
        override fun onResponse(call: Call<Void>, response: Response<Void>) {
            if (response.isSuccessful) {
                val responseBody = response.body()?.toString()
                Log.d("SOAP Response", responseBody ?: "Empty response body")
            } else {
                Log.e("SOAP Error", "Error en la solicitud SOAP: ${response.code()}")
            }
        }

        override fun onFailure(call: Call<Void>, t: Throwable) {
            Log.e("SOAP Error", "Error en la solicitud SOAP: ${t.message}")
        }
    })
}