package com.ajcortes.proyectofinalmoviles.data

import com.google.gson.annotations.SerializedName

data class FullMovie(
    @SerializedName("id") val id: Int,
    @SerializedName("budget") val budget: Int,
    @SerializedName("title") val title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("popularity") val popularity: Number,
    @SerializedName("vote_average") val vote_average: Number,
    @SerializedName("vote_count") val vote_count: Int,
    @SerializedName("poster_path") val poster_path: String,
    @SerializedName("backdrop_path") val backdrop_path: String,
    @SerializedName("revenue") val revenue: Int,
    @SerializedName("runtime") val runtime: Int,
    @SerializedName("release_date") val release_date: String,
    @SerializedName("genres") val genres: Genres
){
    fun toMovie() : Movie{
        return Movie(
            id = id,
            budget = budget,
            title = title,
            overview = overview,
            popularity = popularity,
            vote_average = vote_average,
            vote_count = vote_count,
            poster_path = poster_path,
            backdrop_path = backdrop_path,
            revenue = revenue,
            runtime = runtime,
            release_date = release_date,
            genre = genres.name
        )
    }

    companion object{
        const val SCALE = 20
    }
}

data class Genres(
    @SerializedName("id") val idGenre: Int,
    @SerializedName("name") val name: String
)