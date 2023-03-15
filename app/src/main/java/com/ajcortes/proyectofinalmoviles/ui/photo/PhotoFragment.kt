package com.ajcortes.proyectofinalmoviles.ui.photo

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
import com.ajcortes.proyectofinalmoviles.databinding.FragmentPhotoBinding
import com.ajcortes.proyectofinalmoviles.utils.CameraFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class PhotoFragment : Fragment() {

    private lateinit var binding : FragmentPhotoBinding
    private val photoVM : PhotoVM by viewModels<PhotoVM> { PhotoVM.Factory }

//    private val args : PhotoFragmentArgs by navArgs<PhotoFragmentArgs>()

    private val cameraFragment : CameraFragment by lazy { childFragmentManager.findFragmentById(R.id.fragmentCamera) as CameraFragment}


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPhotoBinding.inflate(inflater,container,false)

        setListeners()

        return binding.root
    }

    private fun setListeners(){
        binding.btnSavePict.setOnClickListener{
            cameraFragment.nombreFoto?.let{
                photoVM.savePhoto(it)
            } ?: Snackbar.make(requireView(), R.string.hacer_foto,Snackbar.LENGTH_SHORT).show()
        }

        binding.btnBackFromPhoto.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setCollectors()
    }

    private fun setCollectors(){
        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                photoVM.uiState.collect{ photoState ->
                    if(photoState.savedPhoto)
                        findNavController().popBackStack()
                }
            }
        }

        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.STARTED){
                cameraFragment.permisos.collect{ permisos ->
                    if(!permisos){
                        Snackbar.make(requireView(), R.string.permisos, Snackbar.LENGTH_SHORT).show()
                        findNavController().popBackStack()
                    }
                }
            }
        }
    }
}