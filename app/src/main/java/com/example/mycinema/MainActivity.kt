package com.example.mycinema

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.mycinema.ui.theme.MyCinemaTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MyCinemaTheme {
                println("AQUI")
                var nowPlayingMovies by remember { mutableStateOf<List<MovieDTO>>(emptyList()) }
                val apiService = RetroFitClient.retrofit.create(ApiService::class.java)
                val callNowPlaying = apiService.getCurrentMovies()
                println("AQUI 2")
                println(nowPlayingMovies)
                println(callNowPlaying.toString())

                callNowPlaying.enqueue(object : Callback<MovieResponse> {
                    override fun onResponse(
                        call: Call<MovieResponse?>,
                        response: Response<MovieResponse?>
                    ) {
                        println("AQUI 4")
                        println(response)
                        if (response.isSuccessful) {
                            val movies = response.body()?.results
                            if (movies != null) {
                                nowPlayingMovies = movies
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

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    //Log.i("TESTE", apiService.getCurrentMovies().toString())
                    LazyColumn {
                        items(nowPlayingMovies) {
                                current ->CurrentMovies(name = current.title, modifier = Modifier.padding(innerPadding), onClick = {
                            Log.i("Name", current.title)
                        }) }
                    }
                }
            }
        }
    }
}

@Composable
fun CurrentMovies(name: String, modifier: Modifier = Modifier, onClick: () -> Unit) {
    Text(
        text = "Hello $name!",
        modifier = modifier.clickable(onClick = onClick)
    )
}


