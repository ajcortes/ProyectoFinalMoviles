package com.ajcortes.proyectofinalmoviles.ui.movieList

import com.ajcortes.proyectofinalmoviles.data.PopularMovie

data class MovieListUiState(
    val isLoading: Boolean = true,
    val movieList: List<PopularMovie> = emptyList()
)