package com.example.mycinema.list.data

import android.accounts.NetworkErrorException
import android.util.Log
import com.example.mycinema.common.data.local.MovieCategory
import com.example.mycinema.common.data.model.Movie
import com.example.mycinema.common.data.remote.model.MovieDTO
import com.example.mycinema.common.data.remote.model.MovieResponse
import com.example.mycinema.list.data.local.LocalDataSource
import com.example.mycinema.list.data.local.MovieListLocalDataSource
import com.example.mycinema.list.data.remote.MovieListRemoteDataSource
import com.example.mycinema.list.data.remote.RemoteDataSource

class MovieListRepository(private val movieListRemoteDataSource: RemoteDataSource, private val movieListLocalDataSource: LocalDataSource) : ListRepository {
//https://developer.android.com/topic/architecture/data-layer?hl=pt-br
    override suspend fun getNowPlaying(): Result<List<Movie>> {
        //var movieConversion = movieListLocalDataSource.getAllByCategoryName(MovieCategory.NOW_PLAYING.toString()).map { mov -> Movie(mov.id, mov.title, mov.overview, mov.image, MovieCategory.NOW_PLAYING.toString()) }

        return try {
            val response = movieListRemoteDataSource.getCurrentMovies()
            val localData = movieListLocalDataSource.getAllByCategoryName(MovieCategory.NOW_PLAYING.toString())
            if (response.isSuccess) {
                if (response.getOrNull()?.size != 0) {
                    // delete per category
                    /*movieListLocalDataSource.deleteByCategoryName(MovieCategory.NOW_PLAYING.name)
                    val movies = response.getOrNull() ?: emptyList<Movie>()
                    movieListLocalDataSource.insertAll(
                        movies,
                        MovieCategory.NOW_PLAYING.name
                    )*/
                    val movies = response.getOrNull() ?: emptyList<Movie>()
                    if (movies.isNotEmpty()) {
                        movieListLocalDataSource.updateByCategoryName(
                            movies,
                            MovieCategory.NOW_PLAYING.toString()
                        )
                    }
                   // movieConversion = response.body()?.results?.map { mov -> Movie(mov.id, mov.title, mov.overview, mov.posterFullPath, MovieCategory.NOW_PLAYING.toString()) }
                    //    ?: emptyList<Movie>()
                }
                val res = movieListLocalDataSource.getAllByCategoryName(MovieCategory.NOW_PLAYING.name) // source of truth

                Result.success(res)
            } else {
                return if (localData.isEmpty()) {
                    Result.failure(NetworkErrorException(response.exceptionOrNull()?.message))
                } else {
                    Result.success(localData)
                }
            }
        }
        catch (ex: Exception) { // no internet connection
            ex.printStackTrace()
            Log.d("Connection", ex.message.toString())
            Result.failure(ex)
            //return movieConversion

        }
    }

    override suspend fun getTopRated(): Result<List<Movie>> {

        return try {
            val response = movieListRemoteDataSource.getTopRatedMovies()
            val localData = movieListLocalDataSource.getAllByCategoryName(MovieCategory.TOP_RATED.toString())

            if (response.isSuccess) {
                if (response.getOrNull()?.size != 0) {
                    // delete per category
                    /*movieListLocalDataSource.deleteByCategoryName(MovieCategory.TOP_RATED.name)
                    val movies = response.getOrNull() ?: emptyList<Movie>()
                    movieListLocalDataSource.insertAll(
                        movies,
                        MovieCategory.TOP_RATED.name
                    )*/
                    val movies = response.getOrNull() ?: emptyList<Movie>()
                    if (movies.isNotEmpty()) {
                        movieListLocalDataSource.updateByCategoryName(
                            movies,
                            MovieCategory.TOP_RATED.toString()
                        )
                    }
                }
                val res = movieListLocalDataSource.getAllByCategoryName(MovieCategory.TOP_RATED.name)

                Result.success(res)
            } else {
                return if (localData.isEmpty()) {
                    Result.failure(NetworkErrorException(response.exceptionOrNull()?.message))
                } else {
                    Result.success(localData)
                }
            }
        }
        catch (ex: Exception) { // no internet connection
            ex.printStackTrace()
            Result.failure(ex)

        }

    }

    override suspend fun getPopularMovies(): Result<List<Movie>> {

        return try {
            val response = movieListRemoteDataSource.getPopularMovies()
            val localData = movieListLocalDataSource.getAllByCategoryName(MovieCategory.POPULAR.toString())

            if (response.isSuccess) {
                if (response.getOrNull()?.size != 0) {
                    // delete per category
                    /*movieListLocalDataSource.deleteByCategoryName(MovieCategory.POPULAR.name)
                    val movies = response.getOrNull() ?: emptyList<Movie>()
                    movieListLocalDataSource.insertAll(
                        movies,
                        MovieCategory.POPULAR.name
                    )*/
                    val movies = response.getOrNull() ?: emptyList<Movie>()
                    if (movies.isNotEmpty()) {
                        movieListLocalDataSource.updateByCategoryName(
                            movies,
                            MovieCategory.POPULAR.toString()
                        )
                    }
                }
                val res = movieListLocalDataSource.getAllByCategoryName(MovieCategory.POPULAR.name)

                Result.success(res)
            } else {
                return if (localData.isEmpty()) {
                    Result.failure(NetworkErrorException(response.exceptionOrNull()?.message))
                } else {
                    Result.success(localData)
                }
            }
        }
        catch (ex: Exception) { // no internet connection
            ex.printStackTrace()
            Result.failure(ex)

        }

    }

    override suspend fun getUpcomingMovies(): Result<List<Movie>> {

        return try {
            val response = movieListRemoteDataSource.getUpcomingMovies()
            val localData = movieListLocalDataSource.getAllByCategoryName(MovieCategory.UPCOMING.toString())

            if (response.isSuccess) {
                if (response.getOrNull()?.size != 0) {
                    // delete per category
                    /*movieListLocalDataSource.deleteByCategoryName(MovieCategory.UPCOMING.name)
                    val movies = response.getOrNull() ?: emptyList<Movie>()
                    movieListLocalDataSource.insertAll(
                        movies,
                        MovieCategory.UPCOMING.name
                    )*/
                    val movies = response.getOrNull() ?: emptyList<Movie>()
                    if (movies.isNotEmpty()) {
                        movieListLocalDataSource.updateByCategoryName(
                            movies,
                            MovieCategory.UPCOMING.toString()
                        )
                    }
                }
                val res = movieListLocalDataSource.getAllByCategoryName(MovieCategory.UPCOMING.name)

                Result.success(res)
            } else {
                return if (localData.isEmpty()) {
                    Result.failure(NetworkErrorException(response.exceptionOrNull()?.message))
                } else {
                    Result.success(localData)
                }
            }
        }
        catch (ex: Exception) { // no internet connection
            ex.printStackTrace()
            Result.failure(ex)

        }

    }
}