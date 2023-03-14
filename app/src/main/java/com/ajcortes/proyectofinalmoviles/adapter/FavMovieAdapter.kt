package com.ajcortes.proyectofinalmoviles.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ajcortes.proyectofinalmoviles.R
import com.ajcortes.proyectofinalmoviles.data.PopularMovie
import com.ajcortes.proyectofinalmoviles.databinding.FavMovieItemBinding
import com.bumptech.glide.Glide

class FavMovieAdapter(
    private var _favMovieList : MutableList<PopularMovie>,
    private val onClickMovie : (Int) -> Unit,
    private val onClickUnfavourite: (Int) -> Unit
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
            onClickUnfavourite: (Int) -> Unit
        ){
            val context = binding.ivPortada.context
//            val idPortada = context.resources.getIdentifier("princess_mononoke", DRAWABLE,context.packageName)
//            binding.ivPortada.setImageResource(idPortada)

            Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500"+movie.poster_path)
                .into(binding.ivPortada)
            binding.tvTitle.text = movie.title
            binding.tvYearRelease.text = movie.release_date
            binding.tvOverviewItem.text = movie.overview

            binding.root.setOnClickListener{
                onClickMovie(movie.id)
            }

            binding.ivStarFill.setOnClickListener{
                onClickUnfavourite(movie.id)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavMovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return FavMovieViewHolder(layoutInflater.inflate(R.layout.fav_movie_item,parent,false))
    }

    override fun onBindViewHolder(holder: FavMovieViewHolder, position: Int) {
        val movie = favMovieList[position]
        holder.bind(movie,onClickMovie,onClickUnfavourite)
    }

    override fun getItemCount(): Int {
        return favMovieList.size
    }

}