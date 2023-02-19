package com.ajcortes.proyectofinalmoviles.api

import com.ajcortes.proyectofinalmoviles.api.MovieApiConfig.Companion.USER_TOKEN
import com.ajcortes.proyectofinalmoviles.data.FullMovie
import com.ajcortes.proyectofinalmoviles.data.Movie
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("movie/{id}?api_key="+USER_TOKEN+"&language=es-ES")
    suspend fun getFullMovie(@Path("id") id : Int) : Response<FullMovie>

//    @GET("movie/popular?api_key="+USER_TOKEN+"&language=es-ES")
//    suspend fun getPopularMovies() : Response<List<FullMovie>>
}