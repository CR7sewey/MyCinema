package com.example.mycinema.list.presentation.ui

import android.util.Log
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.mycinema.ApiService
import com.example.mycinema.common.model.MovieDTO
import com.example.mycinema.common.model.MovieResponse
import com.example.mycinema.common.data.RetroFitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun MovieListScreen(navController: NavHostController) {
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

    MovieListContent(topRated,nowPlayingMovies,popular,upcoming) { id ->
        navController.navigate(route = "movieDetail/${id}")
    }

}

@Composable
private fun MovieListContent(topRated: List<MovieDTO>, nowPlayingMovies: List<MovieDTO>, popular: List<MovieDTO>, upcoming:List<MovieDTO>, onClick: (id: String) -> Unit) {
    Column(modifier = Modifier.fillMaxSize().verticalScroll(rememberScrollState())) {
        Text(
            text = "My Cinema",
            modifier = Modifier.padding(start = 8.dp),
            fontSize = 48.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.size(8.dp))
        MovieSession(
            label = "Now Playing",
            nowPlayingMovies,
            modifier = Modifier.padding(8.dp),
            onClick = onClick)
        MovieSession(
            label = "Top Rated",
            topRated,
            modifier = Modifier.padding(8.dp),
            onClick = onClick)
        MovieSession(
            label = "Popular",
            popular,
            modifier = Modifier.padding(8.dp),
            onClick = onClick)
        MovieSession(
            label = "Upcoming",
            upcoming,
            modifier = Modifier.padding(8.dp),
            onClick = onClick)
    }
}

@Composable
private fun MovieSession(label: String, nowPlayingMovies: List<MovieDTO>, modifier: Modifier, onClick: (title: String) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth().padding(4.dp)) {
        Text(
            text = label,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.size(8.dp))
        MoviesList(movies = nowPlayingMovies,modifier=modifier, onClick=onClick)
    }
}

@Composable
private fun MoviesList(movies: List<MovieDTO>, modifier: Modifier, onClick: (title: String) -> Unit) {
    LazyRow(modifier = modifier.padding(8.dp)) {
        items(movies) { current ->
            MovieItem(movie = current, onClick)
        }
    }
}

@Composable
private fun MovieItem(movie: MovieDTO, onClick: (id: String) -> Unit) {
    Column(modifier = Modifier.padding(end = 4.dp).width(IntrinsicSize.Min).clickable { onClick.invoke(
        movie.id.toString()
    ) }) {
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


