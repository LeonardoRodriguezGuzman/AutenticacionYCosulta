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
package com.lmamlrg.autenticacionycosulta.data

import android.content.Context
import com.lmamlrg.autenticacionycosulta.network.MarsApiService
import retrofit2.Retrofit
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.lmamlrg.autenticacionycosulta.network.AccesoAlumnoApi
import com.lmamlrg.autenticacionycosulta.network.AddCookiesInterceptor
import com.lmamlrg.autenticacionycosulta.network.CookiesInterceptor
import com.lmamlrg.autenticacionycosulta.network.DatosAlumnoApi
import com.lmamlrg.autenticacionycosulta.network.ReceivedCookiesInterceptor
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttp
import okhttp3.OkHttpClient

/**
 * Dependency Injection container at the application level.
 */
interface AppContainer {
    val repositorio: Repositorio
}

/**
 * Implementation for the Dependency Injection container at the application level.
 *
 * Variables are initialized lazily and the same instance is shared across the whole app.
 */
class DefaultAppContainer(applicationContext: Context): AppContainer {
    private val baseUrl = "https://sicenet.surguanajuato.tecnm.mx"

    private var clienteOKHttp : OkHttpClient

    init {
        clienteOKHttp = OkHttpClient()
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(AddCookiesInterceptor(applicationContext))
        builder.addInterceptor(ReceivedCookiesInterceptor(applicationContext))
        clienteOKHttp = builder.build()
    }
    /**
     * Use the Retrofit builder to build a retrofit object using a kotlinx.serialization converter
     */
    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .baseUrl(baseUrl)
        .client(clienteOKHttp)
        .build()

    /**
     * Retrofit service object for creating api calls
     */
    private val retrofitAccesoAlumno: AccesoAlumnoApi by lazy {
        retrofit.create(AccesoAlumnoApi::class.java)
    }
    private val retrofitDatosAlumno: DatosAlumnoApi by lazy {
        retrofit.create((DatosAlumnoApi::class.java))
    }
    override val repositorio: Repositorio by lazy {
        Repositorio(retrofitAccesoAlumno, retrofitDatosAlumno)
    }


    /**
     * DI implementation for Mars photos repository
     */


}



