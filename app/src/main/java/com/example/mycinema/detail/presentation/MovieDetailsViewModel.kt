package com.example.mycinema.detail.presentation

import android.accounts.NetworkErrorException
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.mycinema.common.data.remote.RetroFitClient
import com.example.mycinema.common.data.remote.model.MovieDTO
import com.example.mycinema.detail.data.DetailService
import com.example.mycinema.detail.presentation.ui.MovieDetailsUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.net.UnknownHostException

class MovieDetailsViewModel(private val detailService: DetailService) : ViewModel() {

    private val _uiGetMovie = MutableStateFlow<MovieDetailsUiState?>(null)
    val uiGetMovie: StateFlow<MovieDetailsUiState?> = _uiGetMovie

    // private val callGetMovie = detailService.getMovie(itemId.toString())
    private val _uiErrorFetching = MutableStateFlow<String>("")
    val uiErrorFetching: StateFlow<String> = _uiErrorFetching

    init {
        //fetchData()
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {

            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
                // Get the Application object from extras
                val application = RetroFitClient.retrofit.create(DetailService::class.java)
                // Create a SavedStateHandle for this ViewModel from extras
                //val savedStateHandle = extras.createSavedStateHandle()
                return MovieDetailsViewModel(application as DetailService) as T
            }

        }
    }

    // para limpar o valor quando mudo de screen (so uma activity que fica smepre "viva") (pois nao ta dentro do compose, ele segura o valor (retem os dados)
    // e nao deixa de existir
    // o ideal sera que o VM estja dentro do compose, saiba que ele ja nao existe (observer) e assim limpa isto sozinho)
    fun cleanMovieId() {
        viewModelScope.launch{
            delay(1000)
            _uiGetMovie.value = null
            _uiErrorFetching.value = ""
        }

    }

    fun fetchData(itemId: String) {

        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = detailService.getMovie(itemId.toString())

                if (response.isSuccessful) {
                    val movie = response.body()
                    if (movie != null) {
                        _uiGetMovie.value = MovieDetailsUiState(movie.id,movie.title,movie.overview,movie.posterFullPath)
                    }
                } else {
                    Log.d("MovieDetailsViewModel", "Request Error :: ${response.errorBody()}")
                    println("AQUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII")
                    _uiErrorFetching.value = NetworkErrorException(response.message().toString()).message.toString()
                }
            }
            catch (ex: Exception) {
                ex.printStackTrace()
                println("AQUIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII 222")
                var errorMessage = "Something went wrong..."
                if (ex is UnknownHostException) {
                    errorMessage = "No internet connection..."//ex.message.toString()
                }
                _uiErrorFetching.value = errorMessage
            }
        }
    }

}