package com.example.mycinema

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.mycinema.common.model.MovieDTO
import com.example.mycinema.common.model.MovieResponse
import com.example.mycinema.ui.theme.MyCinemaTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //enableEdgeToEdge()
        setContent {
            MyCinemaTheme {
                println("AQUI")

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    //Log.i("TESTE", apiService.getCurrentMovies().toString())

                    CineNowApp()
                }
            }
        }
    }

    private fun consumeMoviesAPI(call: Call<MovieResponse>): List<MovieDTO> {
        var moviesVariable: List<MovieDTO> = emptyList()
        call.enqueue(object : Callback<MovieResponse> {
            override fun onResponse(
                call: Call<MovieResponse?>,
                response: Response<MovieResponse?>
            ) {
                println("AQUI 4")
                println(response)
                if (response.isSuccessful) {
                    val movies = response.body()?.results
                    if (movies != null) {
                        moviesVariable = movies
                        println("AQUI 3")
                    }
                }
                else {
                    println("AQUI 5")
                    Log.d("MainActivity", "Request Error :: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<MovieResponse?>, t: Throwable) {
                Log.d("MainActivity", "Network Error :: ${t.message}")

            }
        })
        return moviesVariable
    }
}

