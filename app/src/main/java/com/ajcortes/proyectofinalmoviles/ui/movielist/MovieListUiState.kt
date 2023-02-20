package com.ajcortes.proyectofinalmoviles.ui.movielist

import com.ajcortes.proyectofinalmoviles.data.Movie
import com.ajcortes.proyectofinalmoviles.data.PopularMovie

data class MovieListUiState(
    val isLoading: Boolean = true,
    val movieList: List<PopularMovie> = emptyList()
)