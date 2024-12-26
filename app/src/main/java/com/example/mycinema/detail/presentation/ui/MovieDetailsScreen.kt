package com.example.mycinema.detail.presentation.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.mycinema.ApiService
import com.example.mycinema.common.model.MovieDTO
import com.example.mycinema.common.data.RetroFitClient
import com.example.mycinema.detail.presentation.MovieDetailsViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun MovieDetailsScreen(itemId: String, navController: NavHostController, movieDetailVM: MovieDetailsViewModel) {

    val getMovie by movieDetailVM.uiGetMovie.collectAsState()
    movieDetailVM.fetchData(itemId)


    getMovie?.let {
        MovieDetailsContent(it, navController, movieDetailVM)
    }


}

@Composable
private fun MovieDetailsContent(movie: MovieDTO?, navController: NavHostController, movieDetailVM: MovieDetailsViewModel) {
    Column(modifier = Modifier.fillMaxSize().padding(8.dp).verticalScroll(rememberScrollState())) {
       /* LazyRow {
            items(listOf(movie)) {
                    mov ->Text(text = mov?.title ?: "Empty title", fontSize = 48.sp) }
        }*/
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = {
                movieDetailVM.cleanMovieId()
                navController.popBackStack()
            }) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back Button"
                )
            }

            LazyRow {
                items(listOf(movie)) {
                        mov ->Text(text = mov?.title ?: "Empty title", fontSize = 38.sp) }
            }

            /*Text(
                modifier = Modifier.padding(start = 4.dp),
                text = movie?.title ?: "Empty title", fontSize = 48.sp,

            )*/
        }


        AsyncImage(model = movie?.posterFullPath, contentDescription = "${movie?.title} Poster Image",
            modifier = Modifier
                .height(560.dp)
                .fillMaxSize(),
            contentScale = ContentScale.Crop,

            )
        Spacer(modifier = Modifier.size(4.dp))

        Spacer(modifier = Modifier.size(2.dp))

        Text(
            //text = "${movie.overview.slice(IntRange(0,10))}...",
            text = movie?.overview ?: "Empty Description",
            modifier = Modifier.padding(16.dp),
            fontSize = 20.sp

        )
    }
    
}