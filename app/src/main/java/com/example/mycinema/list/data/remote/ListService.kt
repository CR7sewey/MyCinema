package com.example.mycinema.list.data.remote

import com.example.mycinema.common.model.MovieResponse
import retrofit2.Response
import retrofit2.http.GET

interface ListService {
    @GET("movie/now_playing?language=en-US&page=1")
    suspend fun getCurrentMovies(): Response<MovieResponse>

    @GET("movie/top_rated?language=en-US&page=1")
    suspend fun getTopRatedMovies(): Response<MovieResponse>

    @GET("movie/popular?language=en-US&page=1")
    suspend fun getPopularMovies(): Response<MovieResponse>

    @GET("movie/upcoming?language=en-US&page=1")
    suspend fun getUpcomingMovies() : Response<MovieResponse>
}