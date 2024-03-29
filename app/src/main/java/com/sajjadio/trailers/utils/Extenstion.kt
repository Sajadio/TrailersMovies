package com.sajjadio.trailers.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.snackbar.Snackbar
import com.sajjadio.trailers.R
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import java.util.*


fun language() = if (Locale.getDefault().displayLanguage == "English") "en" else "ar"


@SuppressLint("UseCompatLoadingForDrawables")
fun ImageView.loadImageWithSize(url: String, imageSize: String?) {
    imageSize?.let {
        Glide.with(this)
            .load(Constant.IMAGE_PATH + imageSize + url)
            .centerCrop()
            .placeholder(R.color.secondary_color)
            .into(this)
    }
}

fun ImageView.loadImage(url: String) {
    Glide.with(this)
        .load(url)
        .centerCrop()
        .into(this)
}


private var stateActive = false
fun View.setSnackbar(state: Int) {
    if (stateActive) {
        Snackbar.make(this, resources.getString(state), Snackbar.LENGTH_LONG)
            .setBackgroundTint(ContextCompat.getColor(this.context, R.color.green)).show()
        stateActive = false
    }

    when (state) {
        R.string.noConnection -> {
            Snackbar.make(this, resources.getString(state), Snackbar.LENGTH_LONG).show()
            stateActive = true
        }

        R.string.notActive -> {
            Snackbar.make(this, resources.getString(state), Snackbar.LENGTH_LONG)
                .setBackgroundTint(resources.getColor(R.color.redLight)).show()
            stateActive = true
        }
    }

}


@SuppressLint("ServiceCast", "ObsoleteSdkInt")
fun isNetworkAvailable(context: Context): Boolean {
    val connectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        val nw = connectivityManager.activeNetwork ?: return false
        val actNw = connectivityManager.getNetworkCapabilities(nw) ?: return false
        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_BLUETOOTH) -> true
            else -> false
        }
    } else {
        val nwInfo = connectivityManager.activeNetworkInfo ?: return false
        return nwInfo.isConnected
    }
}

fun Int.convertDpToPx(context: Context): Int {
    val density = context.resources.displayMetrics.density
    return (this * density).toInt()
}

fun Context.openLargeImageInDialog(
    imageUrl: String,
    imageSize: String,
    onClickDownloadImage: (Bitmap) -> Unit
) {
    val dialog = Dialog(this)
    dialog.setContentView(R.layout.dialog_image)
    val largeImage = dialog.findViewById<ImageView>(R.id.largeImage)
    val downloadButton = dialog.findViewById<ImageView>(R.id.imageButtonDownload)
    downloadButton.setOnClickListener {
        getBitmap(imageSize, imageUrl, onClickDownloadImage)
    }
    Glide.with(this)
        .load(Constant.IMAGE_PATH + imageSize + imageUrl)
        .into(largeImage)
    dialog.show()
}

private fun Context.getBitmap(
    imageSize: String,
    imageUrl: String,
    onClickDownloadImage: (Bitmap) -> Unit
) {
    Glide.with(this)
        .asBitmap()
        .load(Constant.IMAGE_PATH + imageSize + imageUrl)
        .into(object : CustomTarget<Bitmap>() {
            override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                onClickDownloadImage.invoke(resource)
            }

            override fun onLoadCleared(placeholder: Drawable?) {
                // Do nothing
            }
        })
}

fun Context.saveImageToStorage(bitmap: Bitmap) {
    val imageName = "noo${System.currentTimeMillis()}.jpg"
    var outputStream: OutputStream? = null
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        this.contentResolver?.also { resolver ->
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
        Toast.makeText(this, "Downloaded", Toast.LENGTH_LONG).show()
    }
}

fun Fragment.navigateToAnotherDestination(action: NavDirections) {
    findNavController().navigate(action)
}

fun Fragment.onClickBackButton(view: View) {
    view.setOnClickListener {
        findNavController().popBackStack()
    }
}

fun Activity.playVideo(key: String) {
    val url = Constant.YOUTUBE_BASE + key
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    startActivity(intent)
}
