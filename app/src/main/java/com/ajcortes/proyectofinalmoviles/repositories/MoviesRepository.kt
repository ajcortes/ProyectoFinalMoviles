package com.ajcortes.proyectofinalmoviles.repositories

import com.ajcortes.proyectofinalmoviles.api.ApiService
import com.ajcortes.proyectofinalmoviles.data.FullMovie
import com.ajcortes.proyectofinalmoviles.data.Movie
import retrofit2.Response

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

    suspend fun getPopularMovies(page : Int) : Response<List<Movie>>
    {
        return movieApiService.getPopularMovies()
    }
}