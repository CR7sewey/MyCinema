package com.example.mycinema.list

import android.accounts.NetworkErrorException
import com.example.mycinema.common.data.local.MovieCategory
import com.example.mycinema.common.data.model.Movie
import com.example.mycinema.list.data.local.LocalDataSource
import com.example.mycinema.list.data.MovieListRepository
import com.example.mycinema.list.data.local.MovieListLocalDataSource
import com.example.mycinema.list.data.remote.MovieListRemoteDataSource
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test

class MovieListRepository {

    private val fakeLocalDataSource = FakeMovieListLocalDataSource()
    private val mockRemoteDataSource: MovieListRemoteDataSource = mock()

    private val underTest by lazy {
        MovieListRepository(mockRemoteDataSource, fakeLocalDataSource)
    }

    /**
     * Tests
     * Data being fetched from API
     * Data being fetched from Room
     * Data from API being persisted in
     * No internet connection
     * x4 (nowPlaying, popular, topRated and upcoming)
     */

    @Test
    fun `Given no internet connection and fresh repository and Room Database populated When collecting to nowPlaying Then assert expected value (local data)`() {
        runTest {
            // Given
            // duble
            val moviesAPI = listOf<Movie>(
                Movie(id = 1, title = "Mock Title", overview = "Mock overview", image = "image1", category = MovieCategory.NOW_PLAYING.name)
            )

            whenever(mockRemoteDataSource.getCurrentMovies()).thenReturn(Result.failure(
                NetworkErrorException()))
            fakeLocalDataSource.movies = moviesAPI
            //whenever(fakeLocalDataSource.getAllByCategoryName(MovieCategory.NOW_PLAYING.name)).thenReturn(moviesAPI)

            // when
            val result = underTest.getNowPlaying()

            // Then
            val expected = Result.success(listOf(Movie(id = 1, title = "Mock Title", overview = "Mock overview", image = "image1", category = MovieCategory.NOW_PLAYING.name)))
            assertEquals(expected,result)
        }
    }

    @Test
    fun `Given no internet connection and fresh repository and Room Database not populated When collecting to nowPlaying  Then assert expected value (NetworkErrorException)`() {
        runTest {
            // Given
            // duble
            val moviesAPI = listOf<Movie>(
                Movie(id = 1, title = "Mock Title", overview = "Mock overview", image = "image1", category = MovieCategory.NOW_PLAYING.name)
            )
            val error = Result.failure<List<Movie>>(NetworkErrorException())
            whenever(mockRemoteDataSource.getCurrentMovies()).thenReturn(error)
            fakeLocalDataSource.movies = emptyList<Movie>()
            //whenever(fakeLocalDataSource.getAllByCategoryName(MovieCategory.NOW_PLAYING.name)).thenReturn(emptyList<Movie>())

            // when
            val result = underTest.getNowPlaying()
            // Then
            val expected = error
            assertEquals(expected,result)
        }
    }

    @Test
    fun `Given internet connection and fresh repository and Room Database populated (= API data) When collecting to nowPlaying Then assert expected value (local data - updated)`() {
        runTest {
            // Given
            // duble
            val moviesAPI = listOf<Movie>(
                Movie(id = 1, title = "Mock Title", overview = "Mock overview", image = "image1", category = MovieCategory.NOW_PLAYING.name)
            )

            whenever(mockRemoteDataSource.getCurrentMovies()).thenReturn(Result.success(moviesAPI))
            fakeLocalDataSource.movies = moviesAPI
            //whenever(fakeLocalDataSource.getAllByCategoryName(MovieCategory.NOW_PLAYING.name)).thenReturn(moviesAPI)

            // when
            val result = underTest.getNowPlaying()

            // Then
            val expected = Result.success(listOf(Movie(id = 1, title = "Mock Title", overview = "Mock overview", image = "image1", category = MovieCategory.NOW_PLAYING.name)))
            assertEquals(expected,result)
        }
    }

    @Test
    fun `Given internet connection and fresh repository and Room Database populated (!= API data) When collecting to nowPlaying Then assert expected value (local data - updated)`() {
        runTest {
            // Given
            // duble
            val moviesAPI = listOf<Movie>(
                Movie(id = 1, title = "Mock Title", overview = "Mock overview", image = "image1", category = MovieCategory.NOW_PLAYING.name)
            )
            val moviesLocal = listOf<Movie>(
                Movie(id = 2, title = "Mock Title 2", overview = "Mock overview 2", image = "image2", category = MovieCategory.NOW_PLAYING.name)
            )

            whenever(mockRemoteDataSource.getCurrentMovies()).thenReturn(Result.success(moviesAPI))
            whenever(fakeLocalDataSource.getAllByCategoryName(MovieCategory.NOW_PLAYING.name)).thenReturn(moviesLocal)

            // when
            val result = underTest.getNowPlaying()

            // Then
            val expected = Result.success(moviesAPI)
            assertEquals(expected,result)
            //verify(mockLocalDataSource).updateByCategoryName(moviesAPI,MovieCategory.NOW_PLAYING.name)
        }
    }

}