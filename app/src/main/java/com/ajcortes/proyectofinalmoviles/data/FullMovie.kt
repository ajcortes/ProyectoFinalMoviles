package com.ajcortes.proyectofinalmoviles.data

import com.google.gson.annotations.SerializedName

data class FullMovie(
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("backdrop_path") val backdrop_path: String,
    @SerializedName("belongs_to_collection") val belongs_to_collection: Object,
    @SerializedName("budget") val budget: Int,
    @SerializedName("genres") val genres: List<Genres>,
    @SerializedName("homepage") val homepage: String,
    @SerializedName("id") val id: Int,
    @SerializedName("imdb_id") val imdb_id: String,
    @SerializedName("originial_language") val originial_language: String,
    @SerializedName("originial_title") val originial_title: String,
    @SerializedName("overview") val overview: String,
    @SerializedName("popularity") val popularity: Number,
    @SerializedName("poster_path") val poster_path: String,
    @SerializedName("production_companies") val production_companies: List<ProductionCompanies>,
    @SerializedName("production_countries") val production_countries: List<ProductionCountries>,
    @SerializedName("release_date") val release_date: String,
    @SerializedName("revenue") val revenue: Int,
    @SerializedName("runtime") val runtime: Int,
    @SerializedName("spoken_languages") val spoken_languages: List<SpokenLanguages>,
    @SerializedName("status") val status: String,
    @SerializedName("tagline") val tagline: String,
    @SerializedName("title") val title: String,
    @SerializedName("video") val video: Boolean,
    @SerializedName("vote_average") val vote_average: Number,
    @SerializedName("vote_count") val vote_count: Int,

){
    fun toMovie() : Movie{
        return Movie(
            id = id,
            budget = budget,
            title = title,
            overview = overview,
            popularity = popularity.toInt(),
            vote_average = vote_average.toInt(),
            vote_count = vote_count,
            poster_path = if(poster_path == null) "" else poster_path,
            backdrop_path = if(backdrop_path == null) "" else backdrop_path,
            revenue = revenue,
            runtime = runtime,
            release_date = release_date,
            status = status,
            genre = genres.get(0).name
        )
    }

    companion object{
        const val SCALE = 20
    }
}

data class Genres(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String
)

data class ProductionCompanies(
    @SerializedName("name") val name: String,
    @SerializedName("id") val id: Int,
    @SerializedName("logo_path") val logo_path: String,
    @SerializedName("origin_country") val origin_country: String
)

data class ProductionCountries(
    @SerializedName("iso_3166_1") val iso_3166_1: String,
    @SerializedName("name") val name: String,
)

data class SpokenLanguages(
    @SerializedName("iso_639_1") val iso_639_1: String,
    @SerializedName("name") val name: String,
)