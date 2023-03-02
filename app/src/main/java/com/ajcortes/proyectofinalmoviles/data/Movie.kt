package com.ajcortes.proyectofinalmoviles.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class Movie (
    @PrimaryKey
    val id: Int,
    @ColumnInfo(name= "budget")
    val budget: Int,
    @ColumnInfo(name= "title")
    val title: String,
    @ColumnInfo(name= "overview")
    val overview: String,
    @ColumnInfo(name= "popularity")
    val popularity: Int,
    @ColumnInfo(name= "vote_average")
    val vote_average: Int,
    @ColumnInfo(name= "vote_count")
    val vote_count: Int,
    @ColumnInfo(name= "poster_path")
    val poster_path: String,
    @ColumnInfo(name= "backdrop_path")
    val backdrop_path: String,
    @ColumnInfo(name= "revenue")
    val revenue: Int,
    @ColumnInfo(name= "runtime")
    val runtime: Int,
    @ColumnInfo(name= "release_date")
    val release_date: String,
    @ColumnInfo(name= "status")
    val status: String,
    @ColumnInfo(name= "genre")
    val genre: String
)