package com.example.mycinema

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.mycinema.common.model.MovieDTO
import com.example.mycinema.common.model.MovieResponse
import com.example.mycinema.list.data.ListService
import com.example.mycinema.list.presentation.MovieListViewModel
import com.example.mycinema.ui.theme.MyCinemaTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    private val listViewModel by viewModels<MovieListViewModel> {MovieListViewModel.Factory}
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //enableEdgeToEdge()
        setContent {
            MyCinemaTheme {
                println("AQUI")

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    //Log.i("TESTE", apiService.getCurrentMovies().toString())

                    CineNowApp(listViewModel)
                }
            }
        }
    }
}

