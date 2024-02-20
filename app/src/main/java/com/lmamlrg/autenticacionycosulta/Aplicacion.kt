package com.lmamlrg.autenticacionycosulta

import android.app.Application
import com.lmamlrg.autenticacionycosulta.data.AppContainer
import com.lmamlrg.autenticacionycosulta.data.DefaultAppContainer

class AlumnosApplication: Application() {
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}