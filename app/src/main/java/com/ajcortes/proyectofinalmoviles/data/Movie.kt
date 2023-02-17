package com.ajcortes.proyectofinalmoviles.data


data class Movie (
    val id: Int,
    val budget: Int,
    val title: String,
    val overview: String,
    val popularity: Number,
    val vote_average: Number,
    val vote_count: Int,
    val poster_path: String,
    val backdrop_path: String,
    val revenue: Int,
    val runtime: Int,
    val release_date: String,
    val genre: String
)