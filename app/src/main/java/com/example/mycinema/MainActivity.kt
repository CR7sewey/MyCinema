package com.example.mycinema

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
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
                var nowPlayingMovies by remember { mutableStateOf<List<MovieDTO>>(emptyList()) }
                var topRated by remember { mutableStateOf<List<MovieDTO>>(emptyList()) }
                var popular by remember { mutableStateOf<List<MovieDTO>>(emptyList()) }
                var upcoming by remember { mutableStateOf<List<MovieDTO>>(emptyList()) }
                val apiService = RetroFitClient.retrofit.create(ApiService::class.java)
                val callNowPlaying = apiService.getCurrentMovies()
                val callTopRated = apiService.getTopRatedMovies()
                val callPopular = apiService.getPopularMovies()
                val callUpcoming = apiService.getUpcomingMovies()
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
                //topRated = consumeMoviesAPI(callTopRated)

                callTopRated.enqueue(object : Callback<MovieResponse> {
                    override fun onResponse(
                        call: Call<MovieResponse?>,
                        response: Response<MovieResponse?>
                    ) {
                        println("AQUI 4")
                        println(response)
                        if (response.isSuccessful) {
                            val movies = response.body()?.results
                            if (movies != null) {
                                topRated = movies
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

                callPopular.enqueue(object : Callback<MovieResponse> {
                    override fun onResponse(
                        call: Call<MovieResponse?>,
                        response: Response<MovieResponse?>
                    ) {
                        println("AQUI 4")
                        println(response)
                        if (response.isSuccessful) {
                            val movies = response.body()?.results
                            if (movies != null) {
                                popular = movies
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

                callUpcoming.enqueue(object : Callback<MovieResponse> {
                    override fun onResponse(
                        call: Call<MovieResponse?>,
                        response: Response<MovieResponse?>
                    ) {
                        println("AQUI 4")
                        println(response)
                        if (response.isSuccessful) {
                            val movies = response.body()?.results
                            if (movies != null) {
                                upcoming = movies
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
                    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
                        Text(
                            text = "My Cinema",
                            modifier = Modifier.padding(8.dp),
                            fontSize = 48.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                        Spacer(modifier = Modifier.size(8.dp))
                        MovieSession(
                            label = "Now Playing",
                            nowPlayingMovies,
                            modifier = Modifier.padding(innerPadding),
                            onClick = { title -> Log.i("AQUI", title) })
                        MovieSession(
                            label = "Top Rated",
                            topRated,
                            modifier = Modifier.padding(innerPadding),
                            onClick = { title -> Log.i("AQUI", title) })
                        MovieSession(
                            label = "Popular",
                            popular,
                            modifier = Modifier.padding(innerPadding),
                            onClick = { title -> Log.i("AQUI", title) })
                        MovieSession(
                            label = "Upcoming",
                            upcoming,
                            modifier = Modifier.padding(innerPadding),
                            onClick = { title -> Log.i("AQUI", title) })


                    }

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

@Composable
fun MovieSession(label: String, nowPlayingMovies: List<MovieDTO>, modifier: Modifier, onClick: (title: String) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth().padding(4.dp)) {
        Text(
            text = label,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 4.dp)
        )
        Spacer(modifier = Modifier.size(8.dp))
        MoviesList(movies = nowPlayingMovies,modifier=modifier, onClick=onClick)
    }
}

@Composable
fun MoviesList(movies: List<MovieDTO>, modifier: Modifier, onClick: (title: String) -> Unit) {
    LazyRow(modifier = modifier.padding(8.dp)) {
        items(movies) { current ->
            MovieItem(movie = current, onClick)
        }
    }
}

@Composable
fun MovieItem(movie: MovieDTO, onClick: (title: String) -> Unit) {
    Column(modifier = Modifier.padding(end = 4.dp).width(IntrinsicSize.Min).clickable { onClick.invoke(movie.title) }) {
        AsyncImage(model = movie.posterFullPath, contentDescription = "${movie.title} Poster Image",
            modifier = Modifier
                .width(120.dp)
                .height(150.dp),
            contentScale = ContentScale.Crop)
        Spacer(modifier = Modifier.size(4.dp))

        Text(
           // text = if (movie.title.length > 9) "${movie.title.slice(IntRange(0,9))}..." else movie.title,
            text = movie.title,
            maxLines = 1,
            fontSize = 18.sp,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.SemiBold,
        )
        Spacer(modifier = Modifier.size(2.dp))

        Text(
            //text = "${movie.overview.slice(IntRange(0,10))}...",
            text = movie.overview,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.width(120.dp)

        )
    }
}


