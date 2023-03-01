package com.ajcortes.proyectofinalmoviles.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ajcortes.proyectofinalmoviles.data.Movie
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertMovie(movie: Movie)

    @Delete
    suspend fun deleteMovie(movie: Movie)

    @Query("SELECT * FROM movies")
    fun getAllMovies() : Flow<List<Movie>>

    @Query("SELECT * FROM movies WHERE id LIKE :id")
    suspend fun getMovie(id : Int): Movie?
}