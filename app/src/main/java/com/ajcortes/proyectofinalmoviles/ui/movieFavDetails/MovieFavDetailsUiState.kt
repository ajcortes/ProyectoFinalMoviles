package com.ajcortes.proyectofinalmoviles.ui.movieFavDetails

import com.ajcortes.proyectofinalmoviles.data.Movie

data class MovieFavDetailsUiState (
    val isLoading: Boolean = true,
    val movie : Movie? = null
)