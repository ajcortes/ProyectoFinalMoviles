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
    private val onClickMovie : (String) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    companion object{

    }

    val movieList get() = _movieList

    class MovieViewHolder(view : View) : RecyclerView.ViewHolder(view){

        private val binding = MovieItemBinding.bind(view)

        fun bind(
            movie: Movie,
            onClickMovie: (String) -> Unit
        ){
            binding.textTitle.text = movie.title
            binding.textBudget.text = movie.budget.toString()

            binding.root.setOnClickListener{
                onClickMovie(movie.id.toString())
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return MovieViewHolder(layoutInflater.inflate(R.layout.movie_item,parent,false))
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.bind(movie,onClickMovie)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun setMovieList(movies : List<Movie>){
        _movieList = movies.toMutableList()
    }

}