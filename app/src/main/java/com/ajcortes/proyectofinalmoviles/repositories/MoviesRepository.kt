package com.ajcortes.proyectofinalmoviles.repositories

import com.ajcortes.proyectofinalmoviles.api.ApiService
import com.ajcortes.proyectofinalmoviles.data.*
import com.ajcortes.proyectofinalmoviles.datasource.MoviesDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import retrofit2.Response
import kotlin.random.Random

class MoviesRepository(
    val movieApiService: ApiService,
    private val moviesDao: MoviesDao,
    private val ioDispatcher: CoroutineDispatcher = Dispatchers.IO
) {

    suspend fun insertMovie(movie : Movie) = withContext(ioDispatcher){
        moviesDao.insertMovie(movie)
    }

    suspend fun deleteMovie(movie : Movie) = withContext(ioDispatcher){
        moviesDao.deleteMovie(movie)
    }

    fun getFavMovies() : Flow<List<Movie>>{
        return moviesDao.getAllMovies()
    }

    suspend fun getMovieDT(id: Int) = withContext(ioDispatcher){
        return@withContext moviesDao.getMovie(id)
    }

    companion object{
        const val NUM_MOVIES = 500
    }

    suspend fun getFullMovie(id : Int) : Response<FullMovie>{
        return movieApiService.getFullMovie(id)
    }

    suspend fun getMovie(id : Int) : Response<Movie>{
        var myMovie : Movie? = null
        var fullMovieResp = movieApiService.getFullMovie(id)
        if(fullMovieResp.isSuccessful){
            val fullMovie = fullMovieResp.body()
            fullMovie?.let{
                myMovie = it.toMovie()
            }
            return Response.success(myMovie)
        }else
            return Response.error(null,null)
    }

    suspend fun getRandMovie() : Response<Movie>{
        var myMovie : Movie? = null
        val seed = System.currentTimeMillis()
        var x = (1 .. NUM_MOVIES).random(Random(seed))
        return getMovie(x)
    }

    suspend fun getSomeMovies(num : Int) : Response<List<Movie>>
    {
        var movieList : MutableList<Movie> = mutableListOf()
        for(i in 1 .. num) {
            val movieResp = getRandMovie()
            if(movieResp.isSuccessful){
                movieResp.body()?.let { movieList.add(movieList.size, movieResp.body()!!)}
            }else{
                return Response.error(null,null)
            }
        }

        return Response.success(movieList)
    }

    suspend fun getPopoularMovies() : Response<PopularMoviesResponse> {
        return movieApiService.getPopularMovies()
    }
//
//    suspend fun insertMovie(movie : Movie) = withContext(ioDispatcher){
//        moviesDao.insertMovie(movie)
//    }
//
//    suspend fun deleteMovie(movie : Movie) = withContext(ioDispatcher){
//        moviesDao.deleteMovie(movie)
//    }
//
//    suspend fun getFavMovies() : List<Movie> {
//        return moviesDao.getAllMovies()
//    }
}