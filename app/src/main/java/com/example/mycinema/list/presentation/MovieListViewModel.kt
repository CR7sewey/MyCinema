package com.example.mycinema.list.presentation


import android.accounts.NetworkErrorException
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.mycinema.MyCinemaApplication
import com.example.mycinema.common.data.model.Movie
import com.example.mycinema.common.data.remote.model.MovieResponse
import com.example.mycinema.dependencyInjection.DispatcherIO
import com.example.mycinema.list.data.ListRepository
import com.example.mycinema.list.data.MovieListRepository
import com.example.mycinema.list.presentation.ui.MovieListUiState
import com.example.mycinema.list.presentation.ui.MovieUiData
import dagger.Provides
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject

@HiltViewModel // gera quando viewmodel guarda dados etc
class MovieListViewModel @Inject constructor(private val repository: ListRepository, @DispatcherIO private val dispatcher: CoroutineDispatcher = Dispatchers.IO) : ViewModel() {

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
    /*companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                // Get the Application object from extras
                val application: MyCinemaApplication = checkNotNull(extras[APPLICATION_KEY]) as MyCinemaApplication
                val repository = application.repository
                // Create a SavedStateHandle for this ViewModel from extras
                //val savedStateHandle = extras.createSavedStateHandle()
                return MovieListViewModel(repository) as T
            }

        }
    }*/
    private fun fetchData(option: String) {

        viewModelScope.launch(dispatcher) { // Suspend configuration != callback one
            var response: Result<List<Movie>> = repository.getNowPlaying()
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
                    response = repository.getUpcomingMovies()
                }
            }
            //try {

                if (response.isSuccess) {
                    val movies = response.getOrNull()
                    if (movies != null) {
                        val moviesConverted = movies.map { movieDto -> MovieUiData(id = movieDto.id, title = movieDto.title, overview = movieDto.overview, image = movieDto.image) }
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
                    if (ex is NetworkErrorException) {
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