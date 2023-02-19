package com.ajcortes.proyectofinalmoviles.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ajcortes.proyectofinalmoviles.R
import com.ajcortes.proyectofinalmoviles.data.Movie
import com.ajcortes.proyectofinalmoviles.databinding.MovieItemBinding

class MovieAdapter(
    private var _movieList : MutableList<Movie>,
    private val onClickMovie : (Int) -> Unit,
    private val onClickFavourite: (Int) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    companion object{
        const val DRAWABLE = "drawable"
    }

    val movieList get() = _movieList

    class MovieViewHolder(view : View) : RecyclerView.ViewHolder(view){

        private val binding = MovieItemBinding.bind(view)

        fun bind(
            movie: Movie,
            onClickMovie: (Int) -> Unit,
            onClickFavourite: (Int) -> Unit
        ){
            val context = binding.ivPortada.context
            val idPortada = context.resources.getIdentifier("princess_mononoke", DRAWABLE,context.packageName)
            binding.ivPortada.setImageResource(idPortada)

            binding.tvTitle.text = movie.title
            binding.tvYearRelease.text = movie.release_date
            binding.tvDuration.text = movie.runtime.toString()
            binding.tvGenre.text = movie.genre

            binding.root.setOnClickListener{
                onClickMovie(movie.id)
            }

            binding.ivStar.setOnClickListener{
                onClickFavourite(movie.id)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(layoutInflater.inflate(R.layout.movie_item,parent,false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.bind(movie,onClickMovie,onClickFavourite)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun setMovieList(movies : List<Movie>){
        _movieList = movies.toMutableList()
    }

}