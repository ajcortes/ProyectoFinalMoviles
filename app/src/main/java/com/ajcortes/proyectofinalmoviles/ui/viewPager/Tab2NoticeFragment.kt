package com.ajcortes.proyectoinicio

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ajcortes.proyectofinalmoviles.R
import com.ajcortes.proyectofinalmoviles.data.UserPreferences
import com.ajcortes.proyectofinalmoviles.databinding.FragmentTab2NoticeBinding
import com.ajcortes.proyectofinalmoviles.ui.login.LoginVM

class Tab2NoticeFragment : Fragment() {

    private var _binding : FragmentTab2NoticeBinding? = null
    private val binding
        get() = _binding!!

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
        _binding = FragmentTab2NoticeBinding.inflate(inflater,container,false)
        return binding.root
    }

    val loginVM by viewModels<LoginVM> { LoginVM.Factory }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.checkOcultar.setOnClickListener{
            if(binding.checkOcultar.isChecked){
                loginVM.saveViewPagerVisto(true)
            }
        }

        binding.btnMenu.setOnClickListener {
            findNavController().navigate(R.id.action_noticeFragment_to_menuFragment)
        }

    }

}