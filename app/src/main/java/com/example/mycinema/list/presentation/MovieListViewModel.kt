package com.example.mycinema.list.presentation


import android.util.Log
import androidx.compose.material3.Snackbar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.mycinema.common.data.RetroFitClient
import com.example.mycinema.common.model.MovieDTO
import com.example.mycinema.common.model.MovieResponse
import com.example.mycinema.list.data.ListService
import com.example.mycinema.list.data.MovieListRepository
import com.example.mycinema.list.presentation.ui.MovieListUiState
import com.example.mycinema.list.presentation.ui.MovieUiData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.net.UnknownHostException


class MovieListViewModel(private val repository: MovieListRepository) : ViewModel() {

    private val _uiNowPlayingMovies = MutableStateFlow<MovieListUiState>(MovieListUiState())
    val uiNowPlayingMovies: StateFlow<MovieListUiState> = _uiNowPlayingMovies

    private val _uiTopRated = MutableStateFlow<MovieListUiState>(MovieListUiState())
    val uiTopRated: StateFlow<MovieListUiState> = _uiTopRated

    private val _uiPopular = MutableStateFlow<MovieListUiState>(MovieListUiState())
    val uiPopular: StateFlow<MovieListUiState> = _uiPopular

    private val _uiUpcoming = MutableStateFlow<MovieListUiState>(MovieListUiState())
    val uiUpcoming: StateFlow<MovieListUiState> = _uiUpcoming

    private val _uiErrorFetching = MutableStateFlow<Boolean>(false)
    val uiErrorFetching: StateFlow<Boolean> = _uiErrorFetching

    init {
        fetchData("_uiNowPlayingMovies")
        fetchData("_uiTopRated")
        fetchData("_uiPopular")
        fetchData("_uiUpcoming")

    }
    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                // Get the Application object from extras
                val application = RetroFitClient.retrofit.create(ListService::class.java)
                val repository = MovieListRepository(application)
                // Create a SavedStateHandle for this ViewModel from extras
                //val savedStateHandle = extras.createSavedStateHandle()
                return MovieListViewModel(repository) as T
            }

        }
    }

    private fun fetchData(option: String) {

        viewModelScope.launch(Dispatchers.IO) { // Suspend configuration != callback one
            var response: Result<MovieResponse?> = repository.getNowPlaying()
            when (option) {
                "_uiNowPlayingMovies" -> {
                    _uiNowPlayingMovies.value =
                        MovieListUiState(isLoading = true)
                    response = repository.getNowPlaying()
                }
                "_uiTopRated" -> {
                    _uiTopRated.value = MovieListUiState(isLoading = true)
                    response = repository.getTopRated()

                }
                "_uiPopular" -> {
                    _uiPopular.value = MovieListUiState(isLoading = true)
                    response = repository.getPopularMovies()

                }
                "_uiUpcoming" -> {
                    _uiUpcoming.value = MovieListUiState(isLoading = true)
                    response = repository.getTopRated()
                }
            }
            //try {

                if (response.isSuccess) {
                    val movies = response.getOrNull()?.results
                    if (movies != null) {
                        val moviesConverted = movies.map { movieDto -> MovieUiData(id = movieDto.id, title = movieDto.title, overview = movieDto.overview, image = movieDto.posterFullPath) }
                        when {
                            option == "_uiNowPlayingMovies" -> _uiNowPlayingMovies.value = MovieListUiState(list = moviesConverted, isLoading = false)
                            option == "_uiTopRated" -> _uiTopRated.value = MovieListUiState(list = moviesConverted, isLoading = false)
                            option == "_uiPopular" -> _uiPopular.value = MovieListUiState(list = moviesConverted, isLoading = false)
                            option == "_uiUpcoming" -> _uiUpcoming.value = MovieListUiState(list = moviesConverted, isLoading = false)
                        }
                    }
                } else {
                    val ex = response.exceptionOrNull()
                    var errorMessage = "Something went wrong..."
                    if (ex is UnknownHostException) {
                        errorMessage = "No internet connection..."//ex.message.toString()
                    }
                    Log.d("MovieListViewModel", "Request Error :: ${response.exceptionOrNull()?.message.toString()}")
                    when {
                        option == "_uiNowPlayingMovies" -> _uiNowPlayingMovies.value = MovieListUiState(isLoading = false, isError = true, errorMessage = errorMessage)
                        option == "_uiTopRated" -> _uiTopRated.value = MovieListUiState(isLoading = false, isError = true, errorMessage = errorMessage)
                        option == "_uiPopular" -> _uiPopular.value = MovieListUiState(isLoading = false, isError = true, errorMessage = errorMessage)
                        option == "_uiUpcoming" -> _uiUpcoming.value = MovieListUiState(isLoading = false, isError = true, errorMessage = errorMessage)

                    }
                }
            //}
            /*catch (ex: Exception) { // no internet connection
                ex.printStackTrace()
                var errorMessage = "Something went wrong..."
                if (ex is UnknownHostException) {
                    errorMessage = "No internet connection..."//ex.message.toString()
                }
                when {
                    option.equals("_uiNowPlayingMovies") -> _uiNowPlayingMovies.value = MovieListUiState(isLoading = false, isError = true, errorMessage = errorMessage)
                    option.equals("_uiTopRated") -> _uiTopRated.value = MovieListUiState(isLoading = false, isError = true, errorMessage = errorMessage)
                    option.equals("_uiPopular") -> _uiPopular.value = MovieListUiState(isLoading = false, isError = true, errorMessage = errorMessage)
                    option.equals("_uiUpcoming") -> _uiUpcoming.value = MovieListUiState(isLoading = false, isError = true, errorMessage = errorMessage)

                }
                _uiErrorFetching.value = true
            }*/
        }

    }

}