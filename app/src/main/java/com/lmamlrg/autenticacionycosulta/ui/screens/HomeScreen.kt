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
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.aspectRatio
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.lazy.grid.GridCells
//import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
//import androidx.compose.foundation.lazy.grid.items
//import androidx.compose.material3.Button
//import androidx.compose.material3.Card
//import androidx.compose.material3.CardDefaults
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import coil.compose.AsyncImage
//import coil.request.ImageRequest
//import com.lmamlrg.autenticacionycosulta.R
//import com.lmamlrg.autenticacionycosulta.model.AccesoAlumno
//import com.lmamlrg.autenticacionycosulta.model.MarsPhoto
//import com.lmamlrg.autenticacionycosulta.ui.theme.MarsPhotosTheme
//
//@Composable
//fun HomeScreen(
//    UiState: UiState,
//    retryAction : () -> Unit,
//    modifier: Modifier = Modifier
//) {
//    when (UiState) {
//        is UiState.Loading -> LoadingScreen(modifier = modifier.fillMaxSize())
//        is UiState.Success -> AccesoAlumno(UiState.alumno, modifier)
//        is UiState.Error -> ErrorScreen(retryAction, modifier = modifier.fillMaxSize())
//    }
//}
//
///**
// * The home screen displaying the loading message.
// */
//@Composable
//fun LoadingScreen(modifier: Modifier = Modifier) {
//    Image(
//        modifier = modifier.size(200.dp),
//        painter = painterResource(R.drawable.loading_img),
//        contentDescription = stringResource(R.string.loading)
//    )
//}
//
///**
// * The home screen displaying error message with re-attempt button.
// */
//@Composable
//fun ErrorScreen(retryAction: () -> Unit, modifier: Modifier = Modifier) {
//    Column(
//        modifier = modifier,
//        verticalArrangement = Arrangement.Center,
//        horizontalAlignment = Alignment.CenterHorizontally
//    ) {
//        Image(
//            painter = painterResource(id = R.drawable.ic_connection_error), contentDescription = ""
//        )
//        Text(text = stringResource(R.string.loading_failed), modifier = Modifier.padding(16.dp))
//        Button(onClick = retryAction) {
//            Text(stringResource(R.string.retry))
//        }
//    }
//}
//
///**
// * ResultScreen displaying number of photos retrieved.
// */
//@Composable
//fun ResultScreen(photos: String, modifier: Modifier = Modifier) {
//    Box(
//        contentAlignment = Alignment.Center,
//        modifier = modifier
//    ) {
//        Text(text = photos)
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun LoadingScreenPreview() {
//    MarsPhotosTheme {
//        LoadingScreen()
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun ErrorScreenPreview() {
//    MarsPhotosTheme {
//        ErrorScreen({})
//    }
//}
//
//@Preview(showBackground = true)
//@Composable
//fun PhotosGridScreenPreview() {
//    MarsPhotosTheme {
//        val mockData = AccesoAlumno()
//        AccesoAlumnoScreen(mockData)
//    }
//}
//@Composable
//fun Card(photo: MarsPhoto, modifier: Modifier = Modifier) {
//    Card(
//        modifier = modifier,
//        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
//    ) {
//        AsyncImage(
//            model = ImageRequest.Builder(
//                context = LocalContext
//                    .current
//            )
//                .data(photo.imgSrc)
//                .crossfade(true)
//                .build(),
//            modifier = Modifier.fillMaxWidth(),
//            error = painterResource(R.drawable.ic_broken_image),
//            placeholder = painterResource(R.drawable.loading_img),
//            contentDescription = stringResource(R.string.mars_photo),
//            contentScale = ContentScale.Crop
//        )
//    }
//}
//
//@Composable
//fun AccesoAlumnoScreen(photos:AccesoAlumno, modifier: Modifier = Modifier){
//    LazyVerticalGrid(
//        columns = GridCells.Adaptive(150.dp),
//        modifier = modifier.fillMaxWidth(),
//        contentPadding = PaddingValues(4.dp)
//    ) {
//
//      }
//    }
//}