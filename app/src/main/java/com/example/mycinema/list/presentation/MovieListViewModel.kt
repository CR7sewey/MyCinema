package com.example.mycinema.list.presentation


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.mycinema.common.data.RetroFitClient
import com.example.mycinema.common.model.MovieDTO
import com.example.mycinema.common.model.MovieResponse
import com.example.mycinema.list.data.ListService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import okhttp3.Dispatcher
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MovieListViewModel(private val listService: ListService) : ViewModel() {

    private val _uiNowPlayingMovies = MutableStateFlow<List<MovieDTO>?>(emptyList<MovieDTO>())
    val uiNowPlayingMovies: StateFlow<List<MovieDTO>?> = _uiNowPlayingMovies

    private val _uiTopRated = MutableStateFlow<List<MovieDTO>?>(emptyList<MovieDTO>())
    val uiTopRated: StateFlow<List<MovieDTO>?> = _uiTopRated

    private val _uiPopular = MutableStateFlow<List<MovieDTO>?>(emptyList<MovieDTO>())
    val uiPopular: StateFlow<List<MovieDTO>?> = _uiPopular

    private val _uiUpcoming = MutableStateFlow<List<MovieDTO>?>(emptyList<MovieDTO>())
    val uiUpcoming: StateFlow<List<MovieDTO>?> = _uiUpcoming

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
                // Create a SavedStateHandle for this ViewModel from extras
                //val savedStateHandle = extras.createSavedStateHandle()
                return MovieListViewModel(application as ListService) as T
            }

        }
    }

    private fun fetchData(option: String) {

        viewModelScope.launch(Dispatchers.IO) { // Suspend configuration != callback one
            val response = listService.getCurrentMovies()
            if (response.isSuccessful) {
                val movies = response.body()?.results
                if (movies != null) {
                    when {
                        option.equals("_uiNowPlayingMovies") -> _uiNowPlayingMovies.value = movies
                        option.equals("_uiTopRated") -> _uiTopRated.value = movies
                        option.equals("_uiPopular") -> _uiPopular.value = movies
                        option.equals("_uiUpcoming") -> _uiUpcoming.value = movies
                    }
                }
            }
            else {
                Log.d("MovieListViewModel", "Request Error :: ${response.errorBody()}")
            }
        }

    }

}