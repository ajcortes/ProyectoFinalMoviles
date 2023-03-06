package com.ajcortes.proyectofinalmoviles.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.ajcortes.proyectofinalmoviles.data.UserPreferences
import com.ajcortes.proyectofinalmoviles.dependencies.ProyectoFinalMoviles
import com.ajcortes.proyectofinalmoviles.repositories.UserPreferencesRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class LoginVM(
    private val userPreferencesRepository : UserPreferencesRepository
) : ViewModel() {

    private val _uiState : MutableStateFlow<UserPreferences> = MutableStateFlow(UserPreferences())
    val uiState : StateFlow<UserPreferences> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            updateState()
        }
    }

    private suspend fun updateState(){
        userPreferencesRepository.getUserPreferences().collect{ userPreferencesFlow ->
            _uiState.update { currentState ->
                userPreferencesFlow.copy()
            }
        }
    }

    fun saveUsername(username : String){
        viewModelScope.launch {
            userPreferencesRepository.saveUsername(username)
            _uiState.update{ currenteState ->
                currenteState.copy(
                    username=username,
                    navagcion = true,
                )

            }
        }
    }

    fun saveViewPagerVisto(viewPagerVisto : Boolean){
        viewModelScope.launch {
            userPreferencesRepository.saveViewPagerVisto(viewPagerVisto)
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory{
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(
                modelClass: Class<T>,
                extras:CreationExtras
            ): T {
                val application = checkNotNull(extras[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY])

                return LoginVM(
                    (application as ProyectoFinalMoviles).appContainer.userPreferencesRepository
                ) as T
            }
        }
    }
}