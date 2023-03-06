package com.ajcortes.proyectofinalmoviles.ui.movieFavList

import android.text.Spannable.Factory
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.ajcortes.proyectofinalmoviles.data.Movie
import com.ajcortes.proyectofinalmoviles.data.PopularMovie
import com.ajcortes.proyectofinalmoviles.dependencies.ProyectoFinalMoviles
import com.ajcortes.proyectofinalmoviles.repositories.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieFavListVM(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _uiStateFav : MutableStateFlow<MovieFavListUiState> = MutableStateFlow(
        MovieFavListUiState()
    )

    val uiStateFav : StateFlow<MovieFavListUiState> = _uiStateFav.asStateFlow()

    init {
        getMoviesFav()
    }

    private fun getMoviesFav(){
        viewModelScope.launch{
            moviesRepository.getFavMovies().collect(){ favMovie ->
                _uiStateFav.update {
                    it.copy(
                        favMovieList = favMovie
                    )
                }
            }
        }
    }

    fun unfavouriteMovie(movieId: Int){
        viewModelScope.launch {
            val movie = moviesRepository.getMovie(movieId)
            val movieDef : Movie = movie.body()!!
            val movieDef2 = PopularMovie(movieDef.poster_path,false,movieDef.overview,movieDef.release_date,movieDef.id,movieDef.title,"en",movieDef.title,movieDef.backdrop_path,movieDef.popularity.toFloat(),movieDef.vote_count,false,movieDef.vote_average.toFloat())
            val movieComp = moviesRepository.getMovieDT(movieId)

            if(movieComp!=null)
            {
                moviesRepository.deleteMovie(movieDef2)
            }else
            {

            }
        }
    }

    companion object{
        val Factory:ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass : Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])

                return MovieFavListVM(
                    (application as ProyectoFinalMoviles).appContainer.moviesRepository
                ) as T
            }
        }
    }
}