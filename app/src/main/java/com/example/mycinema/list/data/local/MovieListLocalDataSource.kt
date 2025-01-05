package com.example.mycinema.list.data.local

import android.util.Log
import com.example.mycinema.common.data.local.MovieCategory
import com.example.mycinema.common.data.local.MovieDao
import com.example.mycinema.common.data.local.MovieEntity
import com.example.mycinema.common.data.model.Movie
import com.example.mycinema.common.data.remote.model.MovieDTO

class MovieListLocalDataSource(private val movieDao: MovieDao) {

    fun insertAll(moviesList: List<Movie>, categoryName: String) {
        val movieEntity = moviesList.map { movie -> MovieEntity(movie.id, movie.title, movie.overview, movie.image,
            MovieCategory.valueOf(categoryName).name) }
        movieDao.insertAll(movieEntity)
        //Log.d("TESTE",movieDao.getAllByCategoryName(categoryName).toString())
    }

    fun insertOne(vararg moviesList: MovieEntity) {}

    fun updateByCategoryName(moviesList: List<Movie>, categoryName: String) {
        deleteByCategoryName(categoryName)
       insertAll(moviesList, categoryName)
    }

    fun deleteByCategoryName(categoryName: String) {
        movieDao.deleteByCategoryName(categoryName)
        //movieDao.getAllByCategoryName(categoryName)
        //Log.d("TESTE 1",movieDao.getAllByCategoryName(categoryName).toString())
    }

    fun getAllByCategoryName(categoryName: String): List<Movie> {
        val movies = movieDao.getAllByCategoryName(MovieCategory.valueOf(categoryName).toString())
        return movies.map { mov -> Movie(id = mov.id, mov.title, mov.overview, mov.image, mov.category) }
    }

}