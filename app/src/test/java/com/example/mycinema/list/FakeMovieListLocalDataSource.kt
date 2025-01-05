package com.example.mycinema.list

import com.example.mycinema.common.data.local.MovieEntity
import com.example.mycinema.common.data.model.Movie
import com.example.mycinema.list.data.local.LocalDataSource

class FakeMovieListLocalDataSource: LocalDataSource {
    var movies = emptyList<Movie>()
    override suspend fun insertAll(
        moviesList: List<Movie>,
        categoryName: String
    ) {
        movies = moviesList
    }

    override suspend fun insertOne(vararg moviesList: MovieEntity) {
        TODO("Not yet implemented")
    }

    override suspend fun updateByCategoryName(
        moviesList: List<Movie>,
        categoryName: String
    ) {
        deleteByCategoryName("")
        insertAll(moviesList,"")
    }

    override suspend fun deleteByCategoryName(categoryName: String) {
        movies = emptyList<Movie>()
    }

    override suspend fun getAllByCategoryName(categoryName: String): List<Movie> {
        return movies
    }
}