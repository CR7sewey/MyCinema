package com.example.mycinema.list.data

import android.accounts.NetworkErrorException
import android.util.Log
import com.example.mycinema.common.data.local.MovieCategory
import com.example.mycinema.common.data.model.Movie

interface ListRepository {
    suspend fun getNowPlaying(): Result<List<Movie>>

    suspend fun getTopRated(): Result<List<Movie>>

    suspend fun getPopularMovies(): Result<List<Movie>>

    suspend fun getUpcomingMovies(): Result<List<Movie>>
}