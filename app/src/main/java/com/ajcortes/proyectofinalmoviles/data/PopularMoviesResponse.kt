package com.ajcortes.proyectofinalmoviles.data

import com.google.gson.annotations.SerializedName

data class PopularMoviesResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results : List<PopularMovie>,
    @SerializedName("total_results") val total_results : Int,
    @SerializedName("total_pages") val total_pages : Int
    ){

    companion object{
        const val SCALE = 20
    }
}

data class PopularMovie(
    @SerializedName("poster_path") val poster_path: String,
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("overview") val overview: String,
    @SerializedName("release_date") val release_date: String,
    @SerializedName("genre_ids") val genre_ids: List<Int>,
    @SerializedName("id") val id: Int,
    @SerializedName("originial_title") val originial_title: String,
    @SerializedName("originial_language") val originial_language: String,
    @SerializedName("title") val title: String,
    @SerializedName("backdrop_path") val backdrop_path: String,
    @SerializedName("popularity") val popularity: Number,
    @SerializedName("vote_count") val vote_count: Int,
    @SerializedName("video") val video: Boolean,
    @SerializedName("vote_average") val vote_average: Number
)
