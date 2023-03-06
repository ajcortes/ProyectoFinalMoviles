package com.ajcortes.proyectofinalmoviles.ui.movieFavList

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajcortes.proyectofinalmoviles.R
import com.ajcortes.proyectofinalmoviles.adapter.FavMovieAdapter
import com.ajcortes.proyectofinalmoviles.adapter.MovieAdapter
import com.ajcortes.proyectofinalmoviles.data.Movie
import com.ajcortes.proyectofinalmoviles.data.PopularMovie
import com.ajcortes.proyectofinalmoviles.databinding.FragmentMovieListBinding
import com.ajcortes.proyectofinalmoviles.ui.movieFavList.MovieFavListVM
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import kotlinx.coroutines.launch

class MovieFavListFragment : Fragment() {

    private var _binding : FragmentMovieListBinding? = null
    val binding
        get() = _binding!!

    private val movieFavListVM by viewModels<MovieFavListVM> { MovieFavListVM.Factory }

    private lateinit var favMovieAdapter: FavMovieAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentMovieListBinding.inflate(inflater,container,false)

        binding.textView.text = "Lista de Peliculas"

        binding.butVolver.setOnClickListener{
            findNavController().navigate(R.id.action_movieFavListFragment_to_menuFragment)
        }

        return binding.root
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)



        setCollectors()
    }

    private fun initRecview(movies : MutableList<PopularMovie>){
        favMovieAdapter = FavMovieAdapter(
            movies,
            onClickMovie = { movieId -> selectMovie(movieId)},
            onClickUnfavourite = { movieId -> deleteMovie(movieId)}
        )

        binding.rvMovies.adapter = favMovieAdapter
        binding.rvMovies.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

    }

    private fun deleteMovie(movieId: Int) {
        MaterialAlertDialogBuilder(requireContext()).setTitle("Eliminar").setMessage("¿Quieres eliminar la pelicula?")
            .setPositiveButton("Si"){dialog, which ->
            movieFavListVM.unfavouriteMovie(movieId)
            }
            .setNegativeButton("No"){ dialog, which ->

            }.show()
    }

    private fun setCollectors(){
        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                movieFavListVM.uiStateFav.collect{ movieState ->
                    initRecview(movieState.favMovieList.toMutableList())
                }
            }
        }
    }

    private fun selectMovie(movieId: Int){
        val action = MovieFavListFragmentDirections.actionMovieFavListFragmentToMovieFavDetailsFragment(movieId)
        findNavController().navigate(action)
    }

}