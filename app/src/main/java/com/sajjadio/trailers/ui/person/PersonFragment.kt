package com.sajjadio.trailers.ui.person


import android.content.ContentValues
import android.graphics.Bitmap
import android.graphics.Canvas
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.transition.TransitionInflater
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.sajjadio.trailers.R
import com.sajjadio.trailers.databinding.FragmentPersonBinding
import com.sajjadio.trailers.ui.base.BaseFragment
import com.sajjadio.trailers.ui.details.DetailsFragmentArgs
import com.sajjadio.trailers.ui.person.adapter.PersonAdapter
import com.sajjadio.trailers.ui.person.utils.PersonDetailsDestinationType
import com.sajjadio.trailers.utils.observeEvent
import dagger.hilt.android.AndroidEntryPoint
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream

@AndroidEntryPoint
class PersonFragment :
    BaseFragment<FragmentPersonBinding, PersonViewModel>(R.layout.fragment_person) {

    override val LOG_TAG: String = this::class.java.simpleName
    override val viewModelClass = PersonViewModel::class.java
    private val args: DetailsFragmentArgs by navArgs()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupDetailsRecyclerView()
        observeEventWhenClickItem()
        viewModel.imageUrl.observe(viewLifecycleOwner) {
            saveImageToStorage(it)
        }
    }

    private fun setupDetailsRecyclerView() {
        binding.recyclerViewPerson.adapter = PersonAdapter(viewModel).apply {
            viewModel.responsePersonDetailsData.observe(viewLifecycleOwner) {
                it.data?.let { it1 -> addNestedItem(it1) }
            }
        }
    }

    private fun observeEventWhenClickItem() {
        viewModel.clickItemEvent.observeEvent(viewLifecycleOwner) { destinationType ->
            checkDestinationType(destinationType)
        }
    }

    private fun checkDestinationType(personDetailsDestinationType: PersonDetailsDestinationType) {
        when (personDetailsDestinationType) {

            PersonDetailsDestinationType.Galleries -> {

            }

            PersonDetailsDestinationType.GalleryItem -> {

            }

            PersonDetailsDestinationType.Movies -> {

            }

            PersonDetailsDestinationType.PersonsDetails -> {

            }

            is PersonDetailsDestinationType.MovieItem -> {

            }
        }
    }

    private fun navigateToAnotherDestination(action: NavDirections) {
        findNavController().navigate(action)
    }

    private fun saveImageToStorage(bitmap: Bitmap) {
        val imageName = "noo${System.currentTimeMillis()}.jpg"
        var outputStream: OutputStream? = null
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            requireActivity().contentResolver?.also { resolver ->
                val contentValues = ContentValues().apply {
                    put(MediaStore.MediaColumns.DISPLAY_NAME, imageName)
                    put(MediaStore.MediaColumns.MIME_TYPE, "image/jpg")
                    put(MediaStore.MediaColumns.RELATIVE_PATH, Environment.DIRECTORY_PICTURES)
                }
                val imageUri: Uri? =
                    resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues)
                outputStream = imageUri.let {
                    it?.let { it1 -> resolver.openOutputStream(it1) }
                }
            }
        } else {
            val imageDir =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES)
            val image = File(imageDir, imageName)
            outputStream = FileOutputStream(image)
        }
        outputStream?.use {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, it)
            Toast.makeText(requireContext(), "Downloaded", Toast.LENGTH_LONG).show()
        }
    }
}
