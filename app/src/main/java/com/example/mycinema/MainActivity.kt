package com.example.mycinema

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.mycinema.detail.presentation.MovieDetailsViewModel
import com.example.mycinema.list.presentation.MovieListViewModel
import com.example.mycinema.ui.theme.MyCinemaTheme

class MainActivity : ComponentActivity() {
    private val listViewModel by viewModels<MovieListViewModel> {MovieListViewModel.Factory}
    private val movieDetailVM by viewModels<MovieDetailsViewModel> { MovieDetailsViewModel.Factory}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //enableEdgeToEdge()
        setContent {
            MyCinemaTheme {
                println("AQUI")

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    //Log.i("TESTE", apiService.getCurrentMovies().toString())

                    CineNowApp(listViewModel, movieDetailVM)
                }
            }
        }
    }
}

