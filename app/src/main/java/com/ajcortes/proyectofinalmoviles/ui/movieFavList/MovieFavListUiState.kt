package com.ajcortes.proyectofinalmoviles.ui.movieFavList

import com.ajcortes.proyectofinalmoviles.data.Movie
import com.ajcortes.proyectofinalmoviles.data.PopularMovie

data class MovieFavListUiState(
    val favMovieList : List<PopularMovie> = emptyList(),
    var unfavouriteMovie : Boolean = false,
    var name : String = ""
)