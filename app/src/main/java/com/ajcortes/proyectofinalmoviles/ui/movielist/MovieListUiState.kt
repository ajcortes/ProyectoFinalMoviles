package com.ajcortes.proyectofinalmoviles.ui.movielist

import com.ajcortes.proyectofinalmoviles.data.Movie

data class MovieListUiState (
    val isLoading : Boolean = true,
    val movieList: List<Movie> = emptyList()
)