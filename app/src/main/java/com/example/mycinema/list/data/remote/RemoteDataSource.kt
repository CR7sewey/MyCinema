package com.example.mycinema.list.data.remote

import com.example.mycinema.common.data.model.Movie

interface RemoteDataSource {

    suspend fun getCurrentMovies(): Result<List<Movie>?>

    suspend fun getPopularMovies(): Result<List<Movie>?>

    suspend fun getUpcomingMovies(): Result<List<Movie>?>

    suspend fun getTopRatedMovies(): Result<List<Movie>?>
}