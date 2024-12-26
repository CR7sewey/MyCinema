package com.example.mycinema.list.presentation.ui

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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.mycinema.common.model.MovieDTO
import com.example.mycinema.list.presentation.MovieListViewModel

@Composable
fun MovieListScreen(navController: NavHostController, listViewModel: MovieListViewModel) {

    val topRated by listViewModel.uiTopRated.collectAsState()
    val nowPlayingMovies by listViewModel.uiNowPlayingMovies.collectAsState()
    val popular by listViewModel.uiPopular.collectAsState()
    val upcoming by listViewModel.uiUpcoming.collectAsState()

    MovieListContent(topRated,nowPlayingMovies,popular,upcoming) { id ->
        navController.navigate(route = "movieDetail/${id}")
    }

}

@Composable
private fun MovieListContent(topRated: List<MovieDTO>?, nowPlayingMovies: List<MovieDTO>?, popular: List<MovieDTO>?, upcoming: List<MovieDTO>?, onClick: (String) -> Unit) {
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
private fun MovieSession(label: String, nowPlayingMovies: List<MovieDTO>?, modifier: Modifier, onClick: (String) -> Unit) {
    Column(modifier = Modifier.fillMaxWidth().padding(4.dp)) {
        Text(
            text = label,
            fontSize = 24.sp,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(start = 16.dp)
        )
        Spacer(modifier = Modifier.size(8.dp))
        MoviesList(movies = nowPlayingMovies, modifier =modifier, onClick =onClick)
    }
}

@Composable
private fun MoviesList(movies: List<MovieDTO>?, modifier: Modifier, onClick: (String) -> Unit) {
    LazyRow(modifier = modifier.padding(8.dp)) {
        if (movies != null) {
            items(movies) { current ->
                MovieItem(movie = current, onClick)
            }
        }
    }
}

@Composable
private fun MovieItem(movie: MovieDTO?, onClick: (id: String) -> Unit) {
    Column(modifier = Modifier.padding(end = 4.dp).width(IntrinsicSize.Min).clickable { onClick.invoke(
        movie?.id.toString()
    ) }) {
        AsyncImage(model = movie?.posterFullPath, contentDescription = "${movie?.title} Poster Image",
            modifier = Modifier
                .width(120.dp)
                .height(150.dp),
            contentScale = ContentScale.Crop)
        Spacer(modifier = Modifier.size(4.dp))

        Text(
            // text = if (movie.title.length > 9) "${movie.title.slice(IntRange(0,9))}..." else movie.title,
            text = movie?.title ?: "",
            maxLines = 1,
            fontSize = 18.sp,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.SemiBold,
        )
        Spacer(modifier = Modifier.size(2.dp))

        Text(
            //text = "${movie.overview.slice(IntRange(0,10))}...",
            text = movie?.overview ?: "",
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.width(120.dp)

        )
    }
}


