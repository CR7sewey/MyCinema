package com.example.mycinema

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun CineNowApp(modifier: Modifier = Modifier) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "movieList") {
        composable(route = "movieList") {
            MovieListScreen(navController)
        }
        composable(route = "movieDetail"+ "/{itemId}", arguments = listOf(navArgument("itemId"){ type=
            NavType.StringType})) { backStateEntry ->
            MovieDetailsScreen(requireNotNull(backStateEntry.arguments?.getString("itemId").toString()), navController)
        }
    }
}