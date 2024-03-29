package com.ajcortes.proyectofinalmoviles.ui.movieList

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajcortes.proyectofinalmoviles.R
import com.ajcortes.proyectofinalmoviles.adapter.MovieAdapter
import com.ajcortes.proyectofinalmoviles.data.Movie
import com.ajcortes.proyectofinalmoviles.databinding.FragmentMovieListBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class MovieListFragment : Fragment() {

    private var _binding : FragmentMovieListBinding? = null
    val binding
        get() = _binding!!

    private val movieListVM by viewModels<MovieListVM> { MovieListVM.Factory }

    private lateinit var movieAdapter: MovieAdapter

    private var movieListState : MutableList<Movie> = mutableListOf()


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
            findNavController().navigate(R.id.action_movieListFragment_to_menuFragment)
        }
        return binding.root
    }

    @SuppressLint("UnsafeRepeatOnLifecycleDetector")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        initRecview()

        setCollectors()
    }

    private fun initRecview(){
        movieAdapter = MovieAdapter(
            _movieList = mutableListOf(),
            onClickMovie = { movieId -> selectMovie(movieId)},
            onClickFavourite = { movieId -> favorito(movieId)}
        )

        binding.rvMovies.adapter = movieAdapter
        binding.rvMovies.layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL,false)

    }

    private fun favorito(movieId: Int) {
        MaterialAlertDialogBuilder(requireContext()).setTitle("Añadir").setMessage("¿Quieres añadir la pelicula a favoritos?")
            .setPositiveButton("Si"){dialog, which ->
                movieListVM.insertMovie(movieId)
            }
            .setNegativeButton("No"){ dialog, which ->

            }.show()
    }

    private fun setCollectors(){
        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                movieListVM.uiState.collect{ movieState ->
                    if(!movieState.isLoading){
//                        binding.pbLoading.isVisible = false
                        movieAdapter.setMovieList(movieState.movieList)
                        movieAdapter.notifyDataSetChanged()
                    }else{
//                        binding.pbLoading.isVisible = true
                    }
                }
            }
        }
    }

    private fun selectMovie(movieId: Int){
        val action = MovieListFragmentDirections.actionMovieListFragmentToMovieDetailsFragment(movieId)
        findNavController().navigate(action)
    }


}