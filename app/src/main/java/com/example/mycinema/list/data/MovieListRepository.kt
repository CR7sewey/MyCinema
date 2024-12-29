package com.example.mycinema.list.data

import android.accounts.NetworkErrorException
import com.example.mycinema.common.data.local.MovieCategory
import com.example.mycinema.common.data.model.Movie
import com.example.mycinema.common.data.remote.model.MovieDTO
import com.example.mycinema.common.data.remote.model.MovieResponse
import com.example.mycinema.list.data.local.MovieListLocalDataSource
import com.example.mycinema.list.data.remote.MovieListRemoteDataSource

class MovieListRepository(private val movieListRemoteDataSource: MovieListRemoteDataSource, private val movieListLocalDataSource: MovieListLocalDataSource) {
//https://developer.android.com/topic/architecture/data-layer?hl=pt-br
    suspend fun getNowPlaying(): Result<List<Movie>> {
        //var movieConversion = movieListLocalDataSource.getAllByCategoryName(MovieCategory.NOW_PLAYING.toString()).map { mov -> Movie(mov.id, mov.title, mov.overview, mov.image, MovieCategory.NOW_PLAYING.toString()) }

        return try {
            val response = movieListRemoteDataSource.getCurrentMovies()
            //movieListLocalDataSource.getAllByCategoryName(MovieCategory.NOW_PLAYING.toString())
            if (response.isSuccess) {
                if (response.getOrNull()?.size != 0) {
                    val movies = response.getOrNull() ?: emptyList<Movie>()
                    movieListLocalDataSource.insertAll(
                        movies,
                        MovieCategory.NOW_PLAYING.name
                    )
                   // movieConversion = response.body()?.results?.map { mov -> Movie(mov.id, mov.title, mov.overview, mov.posterFullPath, MovieCategory.NOW_PLAYING.toString()) }
                    //    ?: emptyList<Movie>()
                }
                val res = movieListLocalDataSource.getAllByCategoryName(MovieCategory.NOW_PLAYING.name)

                Result.success(res)
            } else {
                Result.failure(NetworkErrorException(response.exceptionOrNull()?.message))
            }
        }
        catch (ex: Exception) { // no internet connection
            ex.printStackTrace()
            Result.failure(ex)
            //return movieConversion

        }
    }

    suspend fun getTopRated(): Result<List<Movie>> {

        return try {
            val response = movieListRemoteDataSource.getTopRatedMovies()
            if (response.isSuccess) {
                if (response.getOrNull()?.size != 0) {
                    val movies = response.getOrNull() ?: emptyList<Movie>()
                    movieListLocalDataSource.insertAll(
                        movies,
                        MovieCategory.TOP_RATED.name
                    )
                }
                val res = movieListLocalDataSource.getAllByCategoryName(MovieCategory.TOP_RATED.name)

                Result.success(res)
            } else {
                Result.failure(NetworkErrorException(response.exceptionOrNull()?.message))
            }
        }
        catch (ex: Exception) { // no internet connection
            ex.printStackTrace()
            Result.failure(ex)

        }

    }

    suspend fun getPopularMovies(): Result<List<Movie>> {

        return try {
            val response = movieListRemoteDataSource.getPopularMovies()
            if (response.isSuccess) {
                if (response.getOrNull()?.size != 0) {
                    val movies = response.getOrNull() ?: emptyList<Movie>()
                    movieListLocalDataSource.insertAll(
                        movies,
                        MovieCategory.POPULAR.name
                    )
                }
                val res = movieListLocalDataSource.getAllByCategoryName(MovieCategory.POPULAR.name)

                Result.success(res)
            } else {
                Result.failure(NetworkErrorException(response.exceptionOrNull()?.message))
            }
        }
        catch (ex: Exception) { // no internet connection
            ex.printStackTrace()
            Result.failure(ex)

        }

    }

    suspend fun getUpcomingMovies(): Result<List<Movie>> {

        return try {
            val response = movieListRemoteDataSource.getUpcomingMovies()
            if (response.isSuccess) {
                if (response.getOrNull()?.size != 0) {
                    val movies = response.getOrNull() ?: emptyList<Movie>()
                    movieListLocalDataSource.insertAll(
                        movies,
                        MovieCategory.UPCOMING.name
                    )
                }
                val res = movieListLocalDataSource.getAllByCategoryName(MovieCategory.UPCOMING.name)

                Result.success(res)
            } else {
                Result.failure(NetworkErrorException(response.exceptionOrNull()?.message))
            }
        }
        catch (ex: Exception) { // no internet connection
            ex.printStackTrace()
            Result.failure(ex)

        }

    }
}