///*
// * Copyright (C) 2023 The Android Open Source Project
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *     https://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//package com.lmamlrg.autenticacionycosulta.ui.screens
//
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.setValue
//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.ViewModelProvider
//import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
//import androidx.lifecycle.viewModelScope
//import androidx.lifecycle.viewmodel.initializer
//import androidx.lifecycle.viewmodel.viewModelFactory
//import com.lmamlrg.autenticacionycosulta.AlumnoViewModel
//import com.lmamlrg.autenticacionycosulta.MarsPhotosApplication
//import com.lmamlrg.autenticacionycosulta.data.MarsPhotosRepository
//import com.lmamlrg.autenticacionycosulta.data.Repositorio
//import com.lmamlrg.autenticacionycosulta.model.DatosAlumno
//import com.lmamlrg.autenticacionycosulta.model.MarsPhoto
//import kotlinx.coroutines.launch
//import retrofit2.HttpException
//import java.io.IOException
//
///**
// * UI state for the Home screen
// */
//sealed interface UiState {
//    data class Success(val alumno: DatosAlumno) : UiState
//    object Error : UiState
//    object Loading : UiState
//}
//
//class MarsViewModel(private val repositorio: Repositorio) : ViewModel() {
//    /** The mutable State that stores the status of the most recent request */
//    var UiState: UiState by mutableStateOf(UiState.Loading)
//        private set
//
//    /**
//     * Call getMarsPhotos() on init so we can display status immediately.
//     */
//    init {
//        getMarsPhotos()
//    }
//
//    /**
//     * Gets Mars photos information from the Mars API Retrofit service and updates the
//     * [MarsPhoto] [List] [MutableList].
//     */
//    fun getMarsPhotos() {
//        viewModelScope.launch {
//            UiState = UiState.Loading
//            UiState = try {
//                UiState.Success(repositorio.getAlumnoAcademicoWithLineamiento())
//            } catch (e: IOException) {
//                UiState.Error
//            } catch (e: HttpException) {
//                UiState.Error
//            }
//        }
//    }
//
//    /**
//     * Factory for [MarsViewModel] that takes [MarsPhotosRepository] as a dependency
//     */
//    companion object {
//        val Factory: ViewModelProvider.Factory = viewModelFactory {
//            initializer {
//                val application = (this[APPLICATION_KEY] as MarsPhotosApplication)
//                val repositorio = application.container.repositorio
//                AlumnoViewModel(repositorio = Repositorio())
//            }
//        }
//    }
//}
