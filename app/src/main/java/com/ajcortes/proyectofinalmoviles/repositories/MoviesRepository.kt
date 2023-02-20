package com.ajcortes.proyectofinalmoviles.repositories

import com.ajcortes.proyectofinalmoviles.api.ApiService
import com.ajcortes.proyectofinalmoviles.data.*
import retrofit2.Response
import kotlin.random.Random

class MoviesRepository(
    val movieApiService: ApiService
) {

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
}