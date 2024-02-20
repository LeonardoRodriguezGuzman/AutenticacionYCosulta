package com.lmamlrg.autenticacionycosulta.ui.screens

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.lmamlrg.autenticacionycosulta.AlumnosApplication
import com.lmamlrg.autenticacionycosulta.data.Repositorio
import kotlinx.coroutines.async

class LoginView(private val repositorio: Repositorio):ViewModel(){

//    suspend fun getAccess(matricula: String, password: String, tipoUsuario:String): Boolean {
//        return repositorio.getAcceso(matricula, password, tipoUsuario)
//    }

    suspend fun getAlumnoAcademicoWithLineamiento():String{
        val informacion = viewModelScope.async {
            repositorio.getAlumnoAcademicoWithLineamiento()
        }
        return informacion.toString()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as AlumnosApplication)
                val alumnosAplication = application.container.repositorio
                LoginView(repositorio = alumnosAplication)
            }
        }
    }
}

