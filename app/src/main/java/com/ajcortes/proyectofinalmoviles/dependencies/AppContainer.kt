package com.ajcortes.proyectofinalmoviles.dependencies

import android.content.Context
import com.ajcortes.proyectofinalmoviles.api.ApiService
import com.ajcortes.proyectofinalmoviles.api.MovieApiConfig
import com.ajcortes.proyectofinalmoviles.repositories.MoviesRepository

class AppContainer(context : Context) {

    private val movieApiService = MovieApiConfig.provideRetrofit().create(ApiService::class.java)

    val moviesRepository : MoviesRepository = MoviesRepository(movieApiService)
}