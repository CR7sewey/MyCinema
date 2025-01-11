package com.example.mycinema.list.data.local

import com.example.mycinema.common.data.local.MovieCategory
import com.example.mycinema.common.data.local.MovieDao
import com.example.mycinema.common.data.local.MovieEntity
import com.example.mycinema.common.data.model.Movie
import javax.inject.Inject

class MovieListLocalDataSource @Inject constructor(private val movieDao: MovieDao): LocalDataSource {

    override suspend fun insertAll(moviesList: List<Movie>, categoryName: String) {
        val movieEntity = moviesList.map { movie -> MovieEntity(movie.id, movie.title, movie.overview, movie.image,
            MovieCategory.valueOf(categoryName).name) }
        movieDao.insertAll(movieEntity)
        //Log.d("TESTE",movieDao.getAllByCategoryName(categoryName).toString())
    }

    override suspend fun insertOne(vararg moviesList: MovieEntity) {}

    override suspend fun updateByCategoryName(moviesList: List<Movie>, categoryName: String) {
        deleteByCategoryName(categoryName)
       insertAll(moviesList, categoryName)
    }

    override suspend fun deleteByCategoryName(categoryName: String) {
        movieDao.deleteByCategoryName(categoryName)
        //movieDao.getAllByCategoryName(categoryName)
        //Log.d("TESTE 1",movieDao.getAllByCategoryName(categoryName).toString())
    }

    override suspend fun getAllByCategoryName(categoryName: String): List<Movie> {
        val movies = movieDao.getAllByCategoryName(MovieCategory.valueOf(categoryName).toString())
        return movies.map { mov -> Movie(id = mov.id, mov.title, mov.overview, mov.image, mov.category) }
    }

}