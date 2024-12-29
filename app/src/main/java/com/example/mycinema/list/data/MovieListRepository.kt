package com.example.mycinema.list.data

import android.accounts.NetworkErrorException
import com.example.mycinema.common.model.MovieResponse
import com.example.mycinema.list.data.local.MovieListLocalDataSource
import com.example.mycinema.list.data.remote.ListService
import com.example.mycinema.list.data.remote.MovieListRemoteDataSource

class MovieListRepository(private val movieListRemoteDataSource: MovieListRemoteDataSource, movieListLocalDataSource: MovieListLocalDataSource) {

    suspend fun getNowPlaying(): Result<MovieResponse?> {

        return try {
            val response = listService.getCurrentMovies()
            if (response.isSuccessful) {
                Result.success(response.body())
            } else {
                Result.failure(NetworkErrorException(response.message()))
            }
        }
        catch (ex: Exception) { // no internet connection
            ex.printStackTrace()
            Result.failure(ex)

        }
    }

    suspend fun getTopRated(): Result<MovieResponse?> {

        return try {
            val response = listService.getTopRatedMovies()
            if (response.isSuccessful) {
                Result.success(response.body())
            } else {
                Result.failure(NetworkErrorException(response.message()))
            }
        }
        catch (ex: Exception) { // no internet connection
            ex.printStackTrace()
            Result.failure(ex)

        }

    }

    suspend fun getPopularMovies(): Result<MovieResponse?> {

        return try {
            val response = listService.getPopularMovies()
            if (response.isSuccessful) {
                Result.success(response.body())
            } else {
                Result.failure(NetworkErrorException(response.message()))
            }
        }
        catch (ex: Exception) { // no internet connection
            ex.printStackTrace()
            Result.failure(ex)

        }

    }

    suspend fun getUpcomingMovies(): Result<MovieResponse?> {

        return try {
            val response = listService.getUpcomingMovies()
            if (response.isSuccessful) {
                Result.success(response.body())
            } else {
                Result.failure(NetworkErrorException(response.message()))
            }
        }
        catch (ex: Exception) { // no internet connection
            ex.printStackTrace()
            Result.failure(ex)

        }

    }
}