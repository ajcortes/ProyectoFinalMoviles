package com.ajcortes.proyectofinalmoviles.ui.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.findNavController
import com.ajcortes.proyectofinalmoviles.R
import com.ajcortes.proyectofinalmoviles.databinding.FragmentMenuBinding

class MenuFragment : Fragment() {

    private var _binding : FragmentMenuBinding? = null
    val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }
    
    private lateinit var animLatido : Animation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMenuBinding.inflate(inflater,container,false)

        setListeners()
        setAnimations()

        binding.butFilms.startAnimation(animLatido)
        binding.butExit.startAnimation(animLatido)
        binding.butUser.startAnimation(animLatido)
        binding.butFavFilms.startAnimation(animLatido)

        return binding.root
    }

    private fun setAnimations(){
        animLatido = AnimationUtils.loadAnimation(requireContext(), R.anim.anim_latido)
        
        animLatido.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
            }

            override fun onAnimationRepeat(animation: Animation?) {
            }
        })
    }

    private fun setListeners(){
        binding.butFilms.setOnClickListener{
            findNavController().navigate(R.id.action_menuFragment_to_movieListFragment)
        }

        binding.butFavFilms.setOnClickListener{
            findNavController().navigate(R.id.action_menuFragment_to_movieFavListFragment)
        }

        binding.butUser.setOnClickListener{
            findNavController().navigate(R.id.action_menuFragment_to_userFragment)
        }

        binding.butExit.setOnClickListener{
            findNavController().navigate(R.id.action_menuFragment_to_loginFragment)
        }
    }
}