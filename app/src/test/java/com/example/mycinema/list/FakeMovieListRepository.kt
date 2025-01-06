package com.example.mycinema.list

import com.example.mycinema.common.data.model.Movie
import com.example.mycinema.list.data.ListRepository

class FakeMovieListRepository : ListRepository {
    var movies = Result<List<Movie>>.success(emptyList<Movie>())
    override suspend fun getNowPlaying(): Result<List<Movie>> {
        return movies
    }

    override suspend fun getTopRated(): Result<List<Movie>> {
        return movies    }

    override suspend fun getPopularMovies(): Result<List<Movie>> {
        return movies    }

    override suspend fun getUpcomingMovies(): Result<List<Movie>> {
        return movies    }
}