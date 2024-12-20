package com.example.mycinema

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {
    @GET("movie/now_playing?language=en-US&page=1")
    fun getCurrentMovies(): Call<MovieResponse>

    @GET("movie/top_rated?language=en-US&page=1")
    fun getTopRatedMovies(): Call<MovieResponse>

    @GET("discover/movie?include_adult=false&include_video=false&language=en-US&page=1&sort_by=popularity.desc")
    fun getPopularMovies(): Call<MovieResponse>

    @GET("discover/movie?language=en-US&page=1&sort_by=popularity.desc&with_release_type=2|3")
    fun getUpcomingMovies() : Call<MovieResponse>

    @GET("movie/{movie_id}?language=en-US")
    fun getMovie(@Path("movie_id") movieId: String) : Call<MovieDTO>
}