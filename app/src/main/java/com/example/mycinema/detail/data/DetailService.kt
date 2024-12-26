package com.example.mycinema.detail.data

import com.example.mycinema.common.model.MovieDTO
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DetailService {
    @GET("movie/{movie_id}?language=en-US")
    suspend fun getMovie(@Path("movie_id") movieId: String) : Response<MovieDTO>
}