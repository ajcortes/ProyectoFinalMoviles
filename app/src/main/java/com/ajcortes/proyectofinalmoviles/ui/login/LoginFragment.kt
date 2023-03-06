package com.ajcortes.proyectofinalmoviles.ui.login

import android.annotation.SuppressLint
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
import com.ajcortes.proyectofinalmoviles.databinding.FragmentLoginBinding
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    val binding
        get() = _binding!!

    private val loginVM : LoginVM by viewModels<LoginVM> { LoginVM.Factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCollectors()
    }

    @SuppressLint("ResourceType")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        setListeners()

        return binding.root
    }

    private fun setListeners(){
        binding.butLogin.setOnClickListener{
            validateName()
        }
    }

    private fun validateName() {
        if(binding.txtUser.text.toString().isBlank())
        {
            Snackbar.make(requireView(), "Ingrese un nombre de usuario", Snackbar.LENGTH_SHORT).show()
        }else
        {
            loginVM.saveUsername(binding.txtUser.text.toString())
        }
    }

    private fun setCollectors(){
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED){
                loginVM.uiState.collect{ userPreferences->
                    if(userPreferences.navagcion)
                    {
                        if(userPreferences.viewPagerVisto)
                        {
                            Log.d("LoginFragment", "ViewPager visto")
                            findNavController().navigate(R.id.action_loginFragment_to_menuFragment)
                        }else
                        {
                            Log.d("LoginFragment", "ViewPager no visto")
                            findNavController().navigate(R.id.action_loginFragment_to_noticeFragment)
                        }
                    }
                }
            }
        }
    }

}