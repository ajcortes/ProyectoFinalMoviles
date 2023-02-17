package com.ajcortes.proyectofinalmoviles.ui.movielist

import android.text.Spannable.Factory
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

class MovieListVM(
    private val moviesRepository: MoviesRepository
) : ViewModel() {

    private val _uiState : MutableStateFlow<MovieListUiState> = MutableStateFlow(MovieListUiState())
    val uiState : StateFlow<MovieListUiState> = _uiState.asStateFlow()

    init{
        getPopularMovies(PAGE)
    }

    private fun getPopularMovies(page : Int){
        viewModelScope.launch {
            val myMovieResp = moviesRepository.getPopularMovies(page)

            if(myMovieResp.isSuccessful) {
                val myMovies = myMovieResp.body()
                _uiState.update { currentState ->
                    currentState.copy(
                        isLoading = false,
                        movieList = myMovies?.let { it.toList() } ?: emptyList()
                    )
                }
            }else{
                //Fallo
            }
        }
    }

    companion object {
        const val PAGE = 1

        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras: CreationExtras
            ): T {
                val application = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])

                return MovieListVM(
                    (application as ProyectoFinalMoviles).appContainer.moviesRepository
                ) as T
            }
        }
    }
}