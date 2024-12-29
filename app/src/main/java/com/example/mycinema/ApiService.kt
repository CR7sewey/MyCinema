package com.example.mycinema

import com.example.mycinema.common.data.remote.model.MovieDTO
import com.example.mycinema.common.data.remote.model.MovieResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("movie/now_playing?language=en-US&page=1")
    fun getCurrentMovies(): Call<MovieResponse>

    @GET("movie/top_rated?language=en-US&page=1")
    fun getTopRatedMovies(): Call<MovieResponse>

    @GET("movie/popular?language=en-US&page=1")
    fun getPopularMovies(): Call<MovieResponse>

    @GET("movie/upcoming?language=en-US&page=1")
    fun getUpcomingMovies() : Call<MovieResponse>

    @GET("movie/{movie_id}?language=en-US")
    fun getMovie(@Path("movie_id") movieId: String) : Call<MovieDTO>
}