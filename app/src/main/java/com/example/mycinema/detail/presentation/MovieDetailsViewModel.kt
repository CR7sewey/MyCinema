package com.example.mycinema.detail.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.mycinema.common.data.remote.RetroFitClient
import com.example.mycinema.common.data.remote.model.MovieDTO
import com.example.mycinema.detail.data.DetailService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MovieDetailsViewModel(private val detailService: DetailService) : ViewModel() {

    private val _uiGetMovie = MutableStateFlow<MovieDTO?>(null)
    val uiGetMovie: StateFlow<MovieDTO?> = _uiGetMovie

    // private val callGetMovie = detailService.getMovie(itemId.toString())

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
        }

    }

    fun fetchData(itemId: String) {

        viewModelScope.launch(Dispatchers.IO) {
            val response = detailService.getMovie(itemId.toString())
            if (response.isSuccessful) {
                val movie = response.body()
                if (movie != null) {
                    _uiGetMovie.value = movie
                }
            } else {
                Log.d("MovieDetailsViewModel", "Request Error :: ${response.errorBody()}")
            }
        }
    }

}