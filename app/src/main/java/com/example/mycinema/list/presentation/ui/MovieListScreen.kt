package com.example.mycinema.list.presentation.ui

import android.widget.Toast
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.animateValue
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.foundation.progressSemantics
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ProgressIndicatorDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.mycinema.list.presentation.MovieListViewModel

@Composable
fun MovieListScreen(navController: NavHostController, listViewModel: MovieListViewModel = hiltViewModel<MovieListViewModel>()) {

    val topRated by listViewModel.uiTopRated.collectAsState()
    val nowPlayingMovies by listViewModel.uiNowPlayingMovies.collectAsState()
    val popular by listViewModel.uiPopular.collectAsState()
    val upcoming by listViewModel.uiUpcoming.collectAsState()
    val error by listViewModel.uiErrorFetching.collectAsState()

        MovieListContent(topRated, nowPlayingMovies, popular, upcoming) { id ->
            navController.navigate(route = "movieDetail/${id}")
        }


}

@Composable
private fun MovieListContent(topRated: MovieListUiState, nowPlayingMovies: MovieListUiState, popular: MovieListUiState, upcoming: MovieListUiState, onClick: (String) -> Unit) {
    Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {
        Text(
            text = "My Cinema",
            modifier = Modifier.padding(start = 8.dp),
            fontSize = 48.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.size(8.dp))
        MovieSessionOrLoadingOrError("Now Playing", nowPlayingMovies,onClick)
        MovieSessionOrLoadingOrError("Top Rated", topRated,onClick)
        MovieSessionOrLoadingOrError("Popular", popular,onClick)
        MovieSessionOrLoadingOrError("Upcoming", upcoming,onClick)

        }
}

@Composable
fun MovieSessionOrLoadingOrError(label: String, movies: MovieListUiState, onClick: (String) -> Unit, modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Column(modifier = Modifier
        .fillMaxSize()) {
        if (movies.isError == false) {
            MovieSession(
                label = label,
                movies,
                modifier = Modifier.padding(8.dp),
                onClick = onClick
            )
        } else if (movies.isLoading) {
            LoadingMovieListScreen(label = label)
        }
        else if (movies.isError) {
            MovieSession(
                label = label,
                movies,
                modifier = Modifier.padding(8.dp),
                onClick = onClick
            )
            Toast.makeText(context, movies.errorMessage, Toast.LENGTH_LONG).show()
        }
    }
}

@Composable
private fun MovieSession(label: String, nowPlayingMovies: MovieListUiState, modifier: Modifier, onClick: (String) -> Unit) {
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(4.dp)) {
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
private fun MoviesList(movies: MovieListUiState, modifier: Modifier, onClick: (String) -> Unit) {
    LazyRow(modifier = modifier.padding(8.dp)) {
        if (movies != null ) {

                items(movies.list) { current ->
                    MovieItem(movie = current, onClick)
                }

        }
    }
}

@Composable
private fun MovieItem(movie: MovieUiData, onClick: (id: String) -> Unit) {
    Column(modifier = Modifier
        .padding(end = 4.dp)
        .width(IntrinsicSize.Min)
        .clickable {
            onClick.invoke(
                movie.id.toString()
            )
        }) {
        AsyncImage(model = movie.image, contentDescription = "${movie.title} Poster Image",
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

@Composable
private fun LoadingMovieListScreen(label: String) {
    /*Column(modifier = Modifier
        .fillMaxSize()
        .verticalScroll(rememberScrollState())) {
        Text(
            text = "My Cinema",
            modifier = Modifier.padding(start = 8.dp),
            fontSize = 48.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.size(8.dp))
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)
        ) {
            Text(
                text = "Now Playing",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = "Top Rated",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = "Popular",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.size(8.dp))
            Text(
                text = "Upcoming",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 16.dp)
            )
            Spacer(modifier = Modifier.size(96.dp))*/
            Column(modifier = Modifier
                .fillMaxWidth()
                .padding(4.dp)) {
                Text(
                    text = label,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.SemiBold,
                    modifier = Modifier.padding(start = 16.dp)
                )
                Spacer(modifier = Modifier.size(8.dp))
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(30.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center) {
                    Indicator(size = 32.dp)
                    Spacer(modifier = Modifier.size(8.dp))

                    Text(
                        text = "Waiting for internet connection ...",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }


        }

}

@Composable
fun Indicator(
    size: Dp = 32.dp, // indicator size
    sweepAngle: Float = 90f, // angle (lenght) of indicator arc
    color: Color = MaterialTheme.colorScheme.primary, // color of indicator arc line
    strokeWidth: Dp = ProgressIndicatorDefaults.CircularStrokeWidth //width of cicle and ar lines
) {
    ////// animation //////

    // docs recomend use transition animation for infinite loops
    // https://developer.android.com/jetpack/compose/animation
    val transition = rememberInfiniteTransition()

    // define the changing value from 0 to 360.
    // This is the angle of the beginning of indicator arc
    // this value will change over time from 0 to 360 and repeat indefinitely.
    // it changes starting position of the indicator arc and the animation is obtained
    val currentArcStartAngle by transition.animateValue(
        0,
        360,
        Int.VectorConverter,
        infiniteRepeatable(
            animation = tween(
                durationMillis = 1100,
                easing = LinearEasing
            )
        )
    )

    ////// draw /////

    // define stroke with given width and arc ends type considering device DPI
    val stroke = with(LocalDensity.current) {
        Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Square)
    }

    // draw on canvas
    Canvas(
        Modifier
            .progressSemantics() // (optional) for Accessibility services
            .size(size) // canvas size
            .padding(strokeWidth / 2) //padding. otherwise, not the whole circle will fit in the canvas
    ) {
        // draw "background" (gray) circle with defined stroke.
        // without explicit center and radius it fit canvas bounds
        drawCircle(Color.LightGray, style = stroke)

        // draw arc with the same stroke
        drawArc(
            color,
            // arc start angle
            // -90 shifts the start position towards the y-axis
            startAngle = currentArcStartAngle.toFloat() - 90,
            sweepAngle = sweepAngle,
            useCenter = false,
            style = stroke
        )
    }
}