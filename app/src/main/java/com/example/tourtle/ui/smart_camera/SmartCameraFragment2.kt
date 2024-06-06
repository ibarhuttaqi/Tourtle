//package com.example.tourtle.ui.smart_camera
//
//import android.Manifest
//import android.content.Context
//import android.content.Intent
//import android.content.pm.PackageManager
//import android.net.Uri
//import android.os.Build
//import android.os.Bundle
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.OrientationEventListener
//import android.view.Surface
//import android.view.View
//import android.view.ViewGroup
//import android.view.WindowInsets
//import android.view.WindowManager
//import android.widget.TextView
//import android.widget.Toast
//import androidx.camera.core.CameraSelector
//import androidx.camera.core.ImageCapture
//import androidx.camera.core.ImageCaptureException
//import androidx.camera.core.Preview
//import androidx.camera.lifecycle.ProcessCameraProvider
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import androidx.fragment.app.Fragment
//import androidx.lifecycle.ViewModelProvider
//import com.example.tourtle.R
//import com.example.tourtle.databinding.FragmentSmartCameraBinding
//import com.google.common.util.concurrent.ListenableFuture
//import java.io.File
//import java.text.SimpleDateFormat
//import java.util.Locale
//import java.util.concurrent.ExecutorService
//import java.util.concurrent.Executors
//
//class SmartCameraFragment2 : Fragment() {
//
//    private var _binding: FragmentSmartCameraBinding? = null
//
//    // This property is only valid between onCreateView and
//    // onDestroyView.
//    private val binding get() = _binding!!
//
//    private var cameraSelector: CameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
//
//    private var imageCapture: ImageCapture? = null
//
//    private val orientationEventListener by lazy {
//        object : OrientationEventListener(requireContext()) {
//            override fun onOrientationChanged(orientation: Int) {
//                if (orientation == ORIENTATION_UNKNOWN) {
//                    return
//                }
//                val rotation = when (orientation) {
//                    in 45 until 135 -> Surface.ROTATION_270
//                    in 135 until 225 -> Surface.ROTATION_180
//                    in 225 until 315 -> Surface.ROTATION_90
//                    else -> Surface.ROTATION_0
//                }
//                imageCapture?.targetRotation = rotation
//            }
//        }
//    }
//
//    override fun onStart() {
//        super.onStart()
//        orientationEventListener.enable()
//    }
//    override fun onStop() {
//        super.onStop()
//        orientationEventListener.disable()
//    }
//
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View {
//        val smartCameraViewModel =
//            ViewModelProvider(this).get(SmartCameraViewModel::class.java)
//
//        _binding = FragmentSmartCameraBinding.inflate(inflater, container, false)
//        val root: View = binding.root
//
//        return root
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//
//        startCamera()
//
//        binding.captureButton.setOnClickListener {
//            takePhoto()
//        }
//    }
//
//    private fun startCamera() {
//        // showCamera
//        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
//
//        cameraProviderFuture.addListener({
//            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
//            val preview = Preview.Builder()
//                .build()
//                .also {
//                    it.setSurfaceProvider(binding.viewFinder.surfaceProvider)
//                }
//
//            imageCapture = ImageCapture.Builder().build()
//
//            try {
//                cameraProvider.unbindAll()
//                cameraProvider.bindToLifecycle(
//                    this,
//                    cameraSelector,
//                    preview,
//                    imageCapture
//                )
//            } catch (exc: Exception) {
//                Toast.makeText(
//                    requireContext(),
//                    "Gagal memunculkan kamera.",
//                    Toast.LENGTH_SHORT
//                ).show()
//                Log.e(TAG, "startCamera: ${exc.message}")
//            }
//        }, ContextCompat.getMainExecutor(requireContext()))
//    }
//
//    private fun takePhoto() {
//        // takePhoto
//        val imageCapture = imageCapture ?: return
//        val photoFile = createCustomTempFile(requireContext())
//        val outputOptions = ImageCapture.OutputFileOptions.Builder(photoFile).build()
//        imageCapture.takePicture(
//            outputOptions,
//            ContextCompat.getMainExecutor(requireContext()),
//            object : ImageCapture.OnImageSavedCallback {
//                override fun onImageSaved(output: ImageCapture.OutputFileResults) {
////                    Toast.makeText(
////                        this@CameraActivity,
////                        "Berhasil mengambil gambar.",
////                        Toast.LENGTH_SHORT
////                    ).show()
//                    val intent = Intent()
//                    intent.putExtra(EXTRA_CAMERAX_IMAGE, output.savedUri.toString())
//                    requireActivity().setResult(CAMERAX_RESULT, intent)
//                    requireActivity().finish()
//                }
//                override fun onError(exc: ImageCaptureException) {
//                    Toast.makeText(
//                        requireContext(),
//                        "Gagal mengambil gambar.",
//                        Toast.LENGTH_SHORT
//                    ).show()
//                    Log.e(TAG, "onError: ${exc.message}")
//                }
//            }
//        )
//    }
//
//    override fun onDestroyView() {
//        super.onDestroyView()
//        _binding = null
//    }
//
//    companion object {
//        private const val TAG = "CameraActivity"
//        const val EXTRA_CAMERAX_IMAGE = "CameraX Image"
//        const val CAMERAX_RESULT = 200
//    }
//}