package com.example.mycinema.list.data

import com.example.mycinema.common.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET

interface ListService {
    @GET("movie/now_playing?language=en-US&page=1")
    fun getCurrentMovies(): Call<MovieResponse>

    @GET("movie/top_rated?language=en-US&page=1")
    fun getTopRatedMovies(): Call<MovieResponse>

    @GET("movie/popular?language=en-US&page=1")
    fun getPopularMovies(): Call<MovieResponse>

    @GET("movie/upcoming?language=en-US&page=1")
    fun getUpcomingMovies() : Call<MovieResponse>
}