package com.ajcortes.proyectofinalmoviles.datasource

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ajcortes.proyectofinalmoviles.data.Movie

@Database(
    entities = [Movie::class],
    version = 2,
    exportSchema = false
)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun moviesDao() : MoviesDao

    companion object {
        @Volatile
        private var Instance: LocalDatabase? = null

        fun getDatabase(context: Context): LocalDatabase {
            return Instance ?: synchronized(this){
                Room.databaseBuilder(context, LocalDatabase::class.java, "proyecto_final_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }

}