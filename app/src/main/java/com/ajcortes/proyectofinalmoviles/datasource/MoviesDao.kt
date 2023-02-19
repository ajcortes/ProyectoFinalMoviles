package com.ajcortes.proyectofinalmoviles.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ajcortes.proyectofinalmoviles.data.Movie

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertMovie(movie: Movie)

    @Delete
    suspend fun deleteMovie(movie: Movie)

    @Query("SELECT * FROM movies")
    suspend fun getAllMovies() : List<Movie>
}