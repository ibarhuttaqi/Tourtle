package com.example.tourtle.ui.smart_camera.photo_preview

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.tourtle.R
import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Matrix
import android.net.Uri
import android.provider.MediaStore
import android.widget.ImageView
import android.widget.Toast
import androidx.exifinterface.media.ExifInterface
import androidx.navigation.fragment.navArgs
import com.example.tourtle.databinding.FragmentPhotoPreviewBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class PhotoPreviewFragment : Fragment() {

    private var _binding: FragmentPhotoPreviewBinding? = null
    private val binding get() = _binding!!
    private val args: PhotoPreviewFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoPreviewBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val photoUri = Uri.parse(args.photoUri)
        photoUri?.let {
            try {
                val bitmap = BitmapFactory.decodeStream(requireContext().contentResolver.openInputStream(it))
                val rotatedBitmap = bitmap?.let { bmp -> rotateBitmap(bmp, it) }
                binding.photoPreview.setImageBitmap(rotatedBitmap)
            } catch (e: IOException) {
                e.printStackTrace()
                Toast.makeText(requireContext(), "Failed to load image.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.retakeButton.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        binding.saveButton.setOnClickListener {
            savePhotoToGallery(photoUri)
        }
    }

    private fun savePhotoToGallery(uri: Uri) {
        val contentValues = ContentValues().apply {
            put(MediaStore.MediaColumns.DISPLAY_NAME, "photo_${System.currentTimeMillis()}.jpg")
            put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
            put(MediaStore.MediaColumns.RELATIVE_PATH, "Pictures/Tourtle")
        }

        val resolver = requireContext().contentResolver
        val imageUri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
        imageUri?.let {
            val outputStream = resolver.openOutputStream(it)
            val inputStream = resolver.openInputStream(uri)
            outputStream?.let { output ->
                inputStream?.copyTo(output)
                output.close()
            }
            inputStream?.close()

            Toast.makeText(requireContext(), "Photo saved to gallery", Toast.LENGTH_SHORT).show()
        } ?: run {
            Toast.makeText(requireContext(), "Failed to save photo", Toast.LENGTH_SHORT).show()
        }
    }

    private fun rotateBitmap(bitmap: Bitmap, uri: Uri): Bitmap {
        val inputStream = requireContext().contentResolver.openInputStream(uri)
        val exif = ExifInterface(inputStream!!)
        val orientation = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL)
        val matrix = Matrix()

        when (orientation) {
            ExifInterface.ORIENTATION_ROTATE_90 -> matrix.postRotate(90f)
            ExifInterface.ORIENTATION_ROTATE_180 -> matrix.postRotate(180f)
            ExifInterface.ORIENTATION_ROTATE_270 -> matrix.postRotate(270f)
        }

        return Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, matrix, true)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}