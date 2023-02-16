package com.ajcortes.proyectofinalmoviles.api

import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("{id}")
    suspend fun getFullMovie(@Path("id") id : Int) Response<FullMovie>
}