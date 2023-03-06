package com.ajcortes.proyectofinalmoviles.ui.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.ajcortes.proyectofinalmoviles.R
import com.ajcortes.proyectofinalmoviles.databinding.FragmentUserBinding
import kotlinx.coroutines.launch

class UserFragment : Fragment() {

    private var _binding : FragmentUserBinding? = null
    val binding
        get() = _binding!!

    private val loginVM : LoginVM by viewModels<LoginVM> { LoginVM.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentUserBinding.inflate(inflater,container,false)

        setListeners()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCollectors()

    }

    private fun setCollectors() {
        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                loginVM.uiState.collect{ userPreferences ->
                    val check : Boolean =  userPreferences.viewPagerVisto
                    Log.d("CheckBox", check.toString())
                    binding.checkBox.isChecked = userPreferences.viewPagerVisto
                    binding.tvUsername.text = userPreferences.username
                }
            }
        }
    }

    private fun setListeners(){
        binding.checkBox.setOnClickListener {
            if(binding.checkBox.isChecked)
            {
                loginVM.saveViewPagerVisto(true)
            }else
            {
                loginVM.saveViewPagerVisto(false)
            }
        }

        binding.butVolver.setOnClickListener{
            findNavController().navigate(R.id.action_userFragment_to_menuFragment)
        }
    }
}