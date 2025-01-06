package com.example.mycinema.details

import app.cash.turbine.test
import com.example.mycinema.common.data.remote.model.MovieDTO
import com.example.mycinema.detail.data.DetailService
import com.example.mycinema.detail.presentation.MovieDetailsViewModel
import com.example.mycinema.detail.presentation.ui.MovieDetailsUiState
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import kotlinx.coroutines.test.runTest
import retrofit2.Response
import org.junit.Assert.assertEquals
import org.junit.Test

class MovieDetailsViewModel {

    private val mockService: DetailService = mock()

    private val underTest by lazy {
        MovieDetailsViewModel(mockService)
    }

    @Test
    fun `Given fresh viewModel When collecting movie details Then assert movie details`() {
        runTest {
            // Given
            // duble
            val movies = MovieDTO(id = 1, title = "Mock Title", overview = "Mock overview", postPath = "image1")

            whenever(mockService.getMovie("1")).thenReturn(Response.success(movies))


            // When
            underTest.uiGetMovie.test {
                verify(mockService).getMovie("1")
                // Then
                val expected = MovieDetailsUiState(id = 1, title = "Mock Title", overview = "Mock overview", image = "image1")
                assertEquals(expected,awaitItem())
            }

        }
    }


}