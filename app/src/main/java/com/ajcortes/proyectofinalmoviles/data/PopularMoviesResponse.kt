package com.ajcortes.proyectofinalmoviles.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
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

@Entity(tableName = "movies")
data class PopularMovie(
    @ColumnInfo(name= "poster_path")
    @SerializedName("poster_path") val poster_path: String,
    @ColumnInfo(name= "adult")
    @SerializedName("adult") val adult: Boolean,
    @ColumnInfo(name= "overview")
    @SerializedName("overview") val overview: String,
    @ColumnInfo(name= "release_date")
    @SerializedName("release_date") val release_date: String,
//    @SerializedName("genre_ids") val genre_ids: List<Int>,
    @PrimaryKey
    @SerializedName("id") val id: Int,
    @ColumnInfo(name= "original_title")
    @SerializedName("original_title") val original_title: String,
    @ColumnInfo(name= "original_language")
    @SerializedName("original_language") val original_language: String,
    @ColumnInfo(name= "title")
    @SerializedName("title") val title: String,
    @ColumnInfo(name= "backdrop_path")
    @SerializedName("backdrop_path") val backdrop_path: String,
    @ColumnInfo(name= "popularity")
    @SerializedName("popularity") val popularity: Float,
    @ColumnInfo(name= "vote_count")
    @SerializedName("vote_count") val vote_count: Int,
    @ColumnInfo(name= "video")
    @SerializedName("video") val video: Boolean,
    @ColumnInfo(name= "vote_average")
    @SerializedName("vote_average") val vote_average: Float
)
