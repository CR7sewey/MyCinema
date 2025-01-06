package com.example.mycinema.list

import android.accounts.NetworkErrorException
import app.cash.turbine.test
import com.example.mycinema.common.data.local.MovieCategory
import com.example.mycinema.common.data.model.Movie
import com.example.mycinema.list.data.MovieListRepository
import org.junit.Assert.*

import com.example.mycinema.list.presentation.MovieListViewModel
import com.example.mycinema.list.presentation.ui.MovieListUiState
import com.example.mycinema.list.presentation.ui.MovieUiData
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

class MovieListViewModel {

    // duble
    private val mockRepository: MovieListRepository = mock()
    @OptIn(ExperimentalCoroutinesApi::class)
    private val testDispatcher = UnconfinedTestDispatcher(TestCoroutineScheduler())
    private val mockRepository2 = FakeMovieListRepository()

    private val underTest by lazy {
        MovieListViewModel(mockRepository, testDispatcher)
    }

    private val underTest2 by lazy {
        MovieListViewModel(mockRepository2, testDispatcher)
    }

    @Test
    fun `Given fresh viewModel When collecting to topRated Then assert loading state`() {
        runTest {
            // Given
            // duble
            val movies = listOf<Movie>(
                Movie(id = 1, title = "Mock Title", overview = "Mock overview", image = "image1", category = MovieCategory.TOP_RATED.name)
            )
            whenever(mockRepository.getTopRated()).thenReturn(Result.success(movies))

            // When
            val result = underTest.uiTopRated.value

            // Then
            val expected = MovieListUiState(isLoading = true)
            assertEquals(expected,result)

        }
    }

    @Test
    fun `Given fresh viewModel When collecting to nowPlaying Then assert expected value`() {
        runTest {
            // Given
            // duble
            val movies = listOf<Movie>(
                Movie(id = 1, title = "Mock Title", overview = "Mock overview", image = "image1", category = MovieCategory.TOP_RATED.name)
            )
            whenever(mockRepository.getNowPlaying()).thenReturn(Result.success(movies))


            // When
            underTest.uiNowPlayingMovies.test {
                // Then
                val expected = MovieListUiState(list = listOf(MovieUiData(id = 1, title = "Mock Title", overview = "Mock overview", image = "image1")))
                assertEquals(expected,awaitItem())
            }

        }
    }

    @Test
    fun `Given fresh viewModel When collecting to upcoming Then assert expected value`() {
        runTest {
            // Given
            // duble
            val movies = listOf<Movie>(
                Movie(id = 1, title = "Mock Title", overview = "Mock overview", image = "image1", category = MovieCategory.TOP_RATED.name)
            )
            whenever(mockRepository.getUpcomingMovies()).thenReturn(Result.success(movies))


            // When
            underTest.uiUpcoming.test {
                // Then
                val expected = MovieListUiState(list = listOf(MovieUiData(id = 1, title = "Mock Title", overview = "Mock overview", image = "image1")))
                assertEquals(expected,awaitItem())
            }

        }
    }

    @Test
    fun `Given fresh viewModel When collecting to popular Then assert expected value`() {
        runTest {
            // Given
            // duble
            val movies = listOf<Movie>(
                Movie(id = 1, title = "Mock Title", overview = "Mock overview", image = "image1", category = MovieCategory.TOP_RATED.name)
            )
            whenever(mockRepository.getPopularMovies()).thenReturn(Result.success(movies))


            // When
            underTest.uiPopular.test {
                // Then
                val expected = MovieListUiState(list = listOf(MovieUiData(id = 1, title = "Mock Title", overview = "Mock overview", image = "image1")))
                assertEquals(expected,awaitItem())
            }

        }
    }

    @Test
    fun `Given fresh viewModel When collecting to topRated Then assert expected value`() {
        runTest {
            // Given
            // duble
            val movies = listOf<Movie>(
                Movie(id = 1, title = "Mock Title", overview = "Mock overview", image = "image1", category = MovieCategory.TOP_RATED.name)
            )
            whenever(mockRepository.getTopRated()).thenReturn(Result.success(movies))


            // When
            underTest.uiTopRated.test {
                // Then
                val expected = MovieListUiState(list = listOf(MovieUiData(id = 1, title = "Mock Title", overview = "Mock overview", image = "image1")))
                assertEquals(expected,awaitItem())
            }

        }
    }

    @Test
    fun `Given fresh viewModel and no internet connection and no local data When collecting to _ Then assert expected value`() {
        runTest {
            // Given
            // duble
            val movies = listOf<Movie>(
                Movie(id = 1, title = "Mock Title", overview = "Mock overview", image = "image1", category = MovieCategory.TOP_RATED.name)
            )
            //whenever(mockRepository.getTopRated()).thenReturn(Result.failure(NetworkErrorException("No internet connection...")))
            val error = Result.failure<List<Movie>>(NetworkErrorException("No internet connection..."))
            mockRepository2.movies = error

            // when
            underTest2.uiTopRated.test {
                // Then
                val expected = MovieListUiState(isLoading = false, errorMessage = "No internet connection...", isError = true)
                assertEquals(expected, mockRepository2.movies)
            }
        }
    }

}