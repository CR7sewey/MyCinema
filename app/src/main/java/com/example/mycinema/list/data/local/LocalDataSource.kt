package com.example.mycinema.list.data.local

import com.example.mycinema.common.data.local.MovieEntity
import com.example.mycinema.common.data.model.Movie

interface LocalDataSource {

    suspend fun insertAll(moviesList: List<Movie>, categoryName: String)

    suspend fun insertOne(vararg moviesList: MovieEntity)

    suspend fun updateByCategoryName(moviesList: List<Movie>, categoryName: String)

    suspend fun deleteByCategoryName(categoryName: String)

    suspend fun getAllByCategoryName(categoryName: String): List<Movie>
}