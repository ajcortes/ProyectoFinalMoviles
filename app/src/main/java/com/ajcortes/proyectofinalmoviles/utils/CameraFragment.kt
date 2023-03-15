package com.ajcortes.proyectofinalmoviles.utils

import android.content.res.Resources
import android.media.Image
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.ajcortes.proyectofinalmoviles.R
import com.ajcortes.proyectofinalmoviles.databinding.FragmentCameraBinding
import com.bumptech.glide.Glide
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import java.io.File
import android.Manifest

class CameraFragment : Fragment() {

    private lateinit var binding : FragmentCameraBinding

    private lateinit var imageCaptura : ImageCapture

    private val _permisos : MutableStateFlow<Boolean> = MutableStateFlow(true)
    val permisos : StateFlow<Boolean> = _permisos.asStateFlow()

    private var _nombreFoto : String? = null
    val nombreFoto get() = _nombreFoto

    private val cameraPermissionLauncher by lazy{
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ){ isGranted ->
            _permisos.value = isGranted
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCameraBinding.inflate(inflater,container,false)

        cameraPermissionLauncher.launch(Manifest.permission.CAMERA)

        setImageCapture()

        setListeners()

        return binding.root
    }

    private fun setListeners(){
        binding.fabTakePict.setOnClickListener{
            if(binding.viewFinderPict.isVisible){
                captureImage()
                binding.viewFinderPict.isVisible = false
                binding.fabTakePict.setImageResource(R.drawable.camara)
            }else{
                binding.viewFinderPict.isVisible = true
                binding.fabTakePict.setImageResource(R.drawable.lente)
            }
        }
    }

    private fun setImageCapture(){
        imageCaptura = ImageCapture.Builder().apply {
            setCaptureMode(ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY)
        }.build()

        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        val executor = ContextCompat.getMainExecutor(requireContext())

        cameraProviderFuture.addListener({
            val cameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder().build().also{
                it.setSurfaceProvider(binding.viewFinderPict.surfaceProvider)
            }

            val cameraSelector = CameraSelector.DEFAULT_FRONT_CAMERA

            try{
                cameraProvider.unbindAll()

                cameraProvider.bindToLifecycle(this, cameraSelector, preview, imageCaptura)
            }catch (e: Exception){
                Log.e("MyError","Binding failed",e)
            }
        }, executor)
    }

    private fun captureImage() {
        _nombreFoto = "photo_${System.currentTimeMillis()}.jpg"
        val fotoFile = File(
            requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES),_nombreFoto!!
        )

        val outputOptions = ImageCapture.OutputFileOptions.Builder(fotoFile).build()

        imageCaptura.takePicture(
            outputOptions, ContextCompat.getMainExecutor(requireContext()), object : ImageCapture.OnImageSavedCallback{
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    Toast.makeText(requireContext(), "Imagen guardada en: ${fotoFile.absolutePath}", Toast.LENGTH_LONG).show()
                    Glide.with(requireContext())
                        .load(File(requireActivity().getExternalFilesDir(Environment.DIRECTORY_PICTURES), _nombreFoto!!))
                        .override(Resources.getSystem().displayMetrics.widthPixels/2)
                        .circleCrop()
                        .into(binding.ivPicture)
                }

                override fun onError(exception: ImageCaptureException) {
                    Toast.makeText(requireContext(), "Error guardando la imagen: ${exception.message}", Toast.LENGTH_LONG).show()
                    _nombreFoto = null
                }
            }
        )
    }
}