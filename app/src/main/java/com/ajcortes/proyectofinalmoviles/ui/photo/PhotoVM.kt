package com.ajcortes.proyectofinalmoviles.ui.photo

import android.text.Spannable.Factory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.ajcortes.proyectofinalmoviles.dependencies.ProyectoFinalMoviles
import com.ajcortes.proyectofinalmoviles.repositories.UserPreferencesRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PhotoVM(
    private val userPreferencesRepository: UserPreferencesRepository
) : ViewModel() {

    private val _uiState : MutableStateFlow<PhotoUiState> = MutableStateFlow(PhotoUiState())
    val uiState : StateFlow<PhotoUiState> = _uiState.asStateFlow()

    fun setPhoto(name : String){
        viewModelScope.launch {
            _uiState.update { currentState ->
                currentState.copy(
                    photoName = name
                )
            }
        }
    }

    fun savePhoto(photoName : String){
        viewModelScope.launch {
            userPreferencesRepository.savePhoto(photoName)
            _uiState.update { currentState ->
                currentState.copy(
                    savedPhoto = true
                )
            }
        }
    }

    companion object{
        val Factory : ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass : Class<T>,
                extras : CreationExtras
            ) : T {
                val application = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])

                return PhotoVM(
                    (application as ProyectoFinalMoviles).appContainer.userPreferencesRepository
                ) as T
            }
        }
    }
}