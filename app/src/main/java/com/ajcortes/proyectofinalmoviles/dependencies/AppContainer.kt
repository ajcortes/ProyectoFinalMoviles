package com.ajcortes.proyectofinalmoviles.dependencies

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore
import com.ajcortes.proyectofinalmoviles.api.ApiService
import com.ajcortes.proyectofinalmoviles.api.MovieApiConfig
import com.ajcortes.proyectofinalmoviles.data.UserPreferences
import com.ajcortes.proyectofinalmoviles.datasource.LocalDatabase
import com.ajcortes.proyectofinalmoviles.repositories.MoviesRepository
import com.ajcortes.proyectofinalmoviles.repositories.UserPreferencesRepository

val Context.userDataStore by preferencesDataStore(name = UserPreferences.SETTINGS_FILE)

class AppContainer(context : Context) {

    private val movieApiService = MovieApiConfig.provideRetrofit().create(ApiService::class.java)

    val moviesRepository : MoviesRepository = MoviesRepository(movieApiService,LocalDatabase.getDatabase(context).moviesDao())
//
//    private val _moviesRepository : MoviesRepository by lazy{
//        MoviesRepository(movieApiService, LocalDatabase.getDatabase(context).moviesDao())
//    }
//
//    val moviesRepository get() = _moviesRepository

    private val _userPreferencesRepository : UserPreferencesRepository by lazy {
        UserPreferencesRepository(context.userDataStore)
    }

    val userPreferencesRepository get() = _userPreferencesRepository
}