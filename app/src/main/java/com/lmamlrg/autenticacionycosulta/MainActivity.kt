/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.lmamlrg.autenticacionycosulta

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.google.gson.Gson
import com.lmamlrg.autenticacionycosulta.data.DefaultAppContainer
import com.lmamlrg.autenticacionycosulta.model.AccesoAlumno
//import com.lmamlrg.autenticacionycosulta.ui.MarsPhotosApp
import com.lmamlrg.autenticacionycosulta.ui.theme.MarsPhotosTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : ComponentActivity() {

    private val repositorio = DefaultAppContainer(this).repositorio
    private lateinit var gson : Gson
    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        setContent {
            MarsPhotosTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    //MarsPhotosApp()

                }
            }
        }
        GlobalScope.launch {
            try {
                val accesoAlumno = repositorio.getAcceso("","", "ALUMNO")
                gson = Gson()
                val aAlumno = gson.fromJson(accesoAlumno, AccesoAlumno::class.java)
                if (aAlumno.acceso) {
                    mostrarMensaje(applicationContext,
                        "Matricula: ${aAlumno.matricula}," +
                                "Estatus: ${aAlumno.estatus}," +
                                "Tipo de Usuario: ${aAlumno.tipoUsuario}")
                    val datosAlumno = repositorio.getAlumnoAcademicoWithLineamiento()


                }
            } catch (e: Exception){
                Log.e("Error:", "${e.message}")
            }
        }
    }

    suspend fun mostrarMensaje(context: Context, mensaje: String) {
        withContext(Dispatchers.Main) {
            Toast.makeText(context, mensaje, Toast.LENGTH_SHORT).show()
        }
    }
}
