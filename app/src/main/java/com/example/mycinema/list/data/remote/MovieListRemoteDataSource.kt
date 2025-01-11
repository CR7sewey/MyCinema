package com.example.mycinema.list.data.remote

import android.accounts.NetworkErrorException
import com.example.mycinema.common.data.local.MovieCategory
import com.example.mycinema.common.data.model.Movie
import javax.inject.Inject

class MovieListRemoteDataSource @Inject constructor(private val listService: ListService) : RemoteDataSource {

    override suspend fun getCurrentMovies(): Result<List<Movie>?> {
        return try {
            var movieConversion: List<Movie>? = emptyList<Movie>()
            val response = listService.getCurrentMovies()
            if (response.isSuccessful) {
                movieConversion = response.body()?.results?.map { mov -> Movie(mov.id, mov.title, mov.overview, mov.posterFullPath,
                    MovieCategory.NOW_PLAYING.name) }
                Result.success(movieConversion)
            } else {
                Result.failure(NetworkErrorException(response.message()))
            }
        }
        catch (ex: Exception) { // no internet connection
            ex.printStackTrace()
            Result.failure(ex)

        }
    }

    override suspend fun getPopularMovies(): Result<List<Movie>?> {
        return try {
            var movieConversion: List<Movie>? = emptyList<Movie>()

            val response = listService.getPopularMovies()
            if (response.isSuccessful) {
                movieConversion = response.body()?.results?.map { mov -> Movie(mov.id, mov.title, mov.overview, mov.posterFullPath,
                    MovieCategory.POPULAR.name) }
                Result.success(movieConversion)
            } else {
                Result.failure(NetworkErrorException(response.message()))
            }
        }
        catch (ex: Exception) { // no internet connection
            ex.printStackTrace()
            Result.failure(ex)

        }
    }

    override suspend fun getUpcomingMovies(): Result<List<Movie>?> {
        return try {
            var movieConversion: List<Movie>? = emptyList<Movie>()

            val response = listService.getUpcomingMovies()
            if (response.isSuccessful) {
                movieConversion = response.body()?.results?.map { mov -> Movie(mov.id, mov.title, mov.overview, mov.posterFullPath,
                    MovieCategory.UPCOMING.name) }
                Result.success(movieConversion)
            } else {
                Result.failure(NetworkErrorException(response.message()))
            }
        }
        catch (ex: Exception) { // no internet connection
            ex.printStackTrace()
            Result.failure(ex)

        }
    }

    override suspend fun getTopRatedMovies(): Result<List<Movie>?> {
        return try {
            var movieConversion: List<Movie>? = emptyList<Movie>()

            val response = listService.getTopRatedMovies()
            if (response.isSuccessful) {
                movieConversion = response.body()?.results?.map { mov -> Movie(mov.id, mov.title, mov.overview, mov.posterFullPath,
                    MovieCategory.TOP_RATED.name) }
                Result.success(movieConversion)
            } else {
                Result.failure(NetworkErrorException(response.message()))
            }
        }
        catch (ex: Exception) { // no internet connection
            ex.printStackTrace()
            Result.failure(ex)

        }
    }

}