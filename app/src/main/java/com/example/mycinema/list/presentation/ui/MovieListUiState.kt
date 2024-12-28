package com.example.mycinema.list.presentation.ui

import com.google.gson.annotations.SerializedName

data class MovieListUiState(
    val list: List<MovieUiData> = emptyList<MovieUiData>(),
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = "Something went wrong",
)

data class MovieUiData(
    val id: Int,
    val title: String,
    val overview: String,
    val image: String,
)
