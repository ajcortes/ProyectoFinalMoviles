package com.ajcortes.proyectofinalmoviles.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class MovieApiConfig {
    companion object{

        const val USER_TOKEN = "fc94e4d545d2cf8f8466eaa35de317d8"
        const val BASE_URL = "https://api.themoviedb.org/3/"

        fun provideRetrofit() : Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}