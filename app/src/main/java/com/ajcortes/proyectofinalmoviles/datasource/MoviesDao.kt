package com.ajcortes.proyectofinalmoviles.datasource

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ajcortes.proyectofinalmoviles.data.Movie
import com.ajcortes.proyectofinalmoviles.data.PopularMovie
import kotlinx.coroutines.flow.Flow

@Dao
interface MoviesDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertMovie(movie: PopularMovie)

    @Delete
    suspend fun deleteMovie(movie: PopularMovie)

    @Query("SELECT * FROM movies")
    fun getAllMovies() : Flow<List<PopularMovie>>

    @Query("SELECT * FROM movies WHERE id LIKE :id")
    suspend fun getMovie(id : Int): PopularMovie?
}