package com.ajcortes.proyectofinalmoviles.ui.movieDetails

import com.ajcortes.proyectofinalmoviles.data.Movie

data class MovieDetailsUiState (
    val isLoading: Boolean = true,
    val movie : Movie? = null
)