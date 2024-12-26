package com.example.mycinema

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mycinema.detail.presentation.ui.MovieDetailsScreen
import com.example.mycinema.list.presentation.MovieListViewModel
import com.example.mycinema.list.presentation.ui.MovieListScreen

@Composable
fun CineNowApp(listViewModel: MovieListViewModel, modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "movieList") {
        composable(route = "movieList") {
            MovieListScreen(navController, listViewModel)
        }
        composable(route = "movieDetail"+ "/{itemId}", arguments = listOf(navArgument("itemId"){ type=
            NavType.StringType})) { backStateEntry ->
            MovieDetailsScreen(
                requireNotNull(
                    backStateEntry.arguments?.getString("itemId").toString()
                ), navController
            )
        }
    }
}