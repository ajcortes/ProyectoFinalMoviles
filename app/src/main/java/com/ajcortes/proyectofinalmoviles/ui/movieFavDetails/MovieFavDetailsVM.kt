package com.ajcortes.proyectofinalmoviles.ui.movieFavDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.ajcortes.proyectofinalmoviles.dependencies.ProyectoFinalMoviles
import com.ajcortes.proyectofinalmoviles.repositories.MoviesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MovieFavDetailsVM( private val moviesRepository: MoviesRepository) : ViewModel() {

    private val _uiState : MutableStateFlow<MovieFavDetailsUiState> = MutableStateFlow(
        MovieFavDetailsUiState()
    )

    val uiState : StateFlow<MovieFavDetailsUiState> = _uiState.asStateFlow()

    init {
    }

    fun setMovie(idMovie: Int){
        viewModelScope.launch {
            val movieResp = moviesRepository.getMovie(idMovie)
            if(movieResp.isSuccessful){
                val movie = movieResp.body()
                _uiState.update {
                    it.copy(
                        isLoading = false,
                        movie = movie
                    )
                }
            }else{
                //Error
            }
        }
    }

    companion object{
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass : Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])

                return MovieFavDetailsVM(
                    (application as ProyectoFinalMoviles).appContainer.moviesRepository
                ) as T
            }
        }
    }
}