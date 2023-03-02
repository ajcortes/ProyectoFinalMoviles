package com.ajcortes.proyectofinalmoviles.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ajcortes.proyectofinalmoviles.R
import com.ajcortes.proyectofinalmoviles.data.PopularMovie
import com.ajcortes.proyectofinalmoviles.databinding.FavMovieItemBinding

class FavMovieAdapter(
    private var _favMovieList : MutableList<PopularMovie>,
    private val onClickMovie : (Int) -> Unit,
    private val onClickFavourite: (Int) -> Unit
) : RecyclerView.Adapter<FavMovieAdapter.FavMovieViewHolder>() {

    companion object{
        const val DRAWABLE = "drawable"
    }

    val favMovieList get() = _favMovieList

    class FavMovieViewHolder(view : View) : RecyclerView.ViewHolder(view){

        private val binding = FavMovieItemBinding.bind(view)

        fun bind(
            movie: PopularMovie,
            onClickMovie: (Int) -> Unit,
            onClickFavourite: (Int) -> Unit
        ){
            val context = binding.ivPortada.context
            val idPortada = context.resources.getIdentifier("princess_mononoke", DRAWABLE,context.packageName)
            binding.ivPortada.setImageResource(idPortada)

            binding.tvTitle.text = movie.title
            binding.tvYearRelease.text = movie.release_date
//            binding.tvDuration.text = movie.runtime.toString()
//            binding.tvGenre.text = movie.genre

            binding.root.setOnClickListener{
                onClickMovie(movie.id)
            }

            binding.ivStar.setOnClickListener{
                onClickFavourite(movie.id)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavMovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FavMovieViewHolder(layoutInflater.inflate(R.layout.movie_item,parent,false))
    }

    override fun onBindViewHolder(holder: FavMovieViewHolder, position: Int) {
        val movie = favMovieList[position]
        holder.bind(movie,onClickMovie,onClickFavourite)
    }

    override fun getItemCount(): Int {
        return favMovieList.size
    }

    fun setMovieList(movies : List<PopularMovie>){
        _favMovieList = movies.toMutableList()
    }

}