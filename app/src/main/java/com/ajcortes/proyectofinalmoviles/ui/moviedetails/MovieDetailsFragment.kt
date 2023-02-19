package com.ajcortes.proyectofinalmoviles.ui.moviedetails

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
import androidx.navigation.fragment.navArgs
import com.ajcortes.proyectofinalmoviles.R
import com.ajcortes.proyectofinalmoviles.databinding.FragmentMovieDetailsBinding
import com.ajcortes.proyectofinalmoviles.ui.movielist.MovieListVM.Companion.Factory
import kotlinx.coroutines.launch

class MovieDetailsFragment : Fragment() {

    companion object {
        const val DRAWABLE = "drawable"
    }

    private var _binding : FragmentMovieDetailsBinding? = null
    private val binding
        get() = _binding!!

    val args : MovieDetailsFragmentArgs by navArgs()

    private val movieDetailsVM by viewModels<MovieDetailsVM> { MovieDetailsVM.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMovieDetailsBinding.inflate(inflater,container,false)

        movieDetailsVM.setMovie(args.idMovie)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCollectors()

        binding.butVolverDetail.setOnClickListener{
            val action = MovieDetailsFragmentDirections.actionMovieDetailsFragmentToMovieListFragment()
            findNavController().navigate(action)
        }
    }

    private fun setCollectors(){
        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                movieDetailsVM.uiState.collect{ movieState ->
                    if(!movieState.isLoading){
//                        binding.pbLoadingDetails!!.isVisible = false
                        movieState.movie?.let{
                            binding.tvJapan.text = it.title
                            binding.tvBudgetDetails.text = it.budget.toString() + "$"
                            binding.tvRevenue.text = it.revenue.toString() + "$"
                            binding.tvState.text = it.status
                            binding.tvGenreDetail.text = it.genre
                            binding.tvTime.text = it.runtime.toString()+ " mins"
                            binding.tvYear.text = it.release_date
                            binding.tvOverview.text = it.overview
                        }
                    }else{
//                        binding.pbLoadingDetails!!.isVisible = true
                    }
                }
            }
        }
    }
}




































