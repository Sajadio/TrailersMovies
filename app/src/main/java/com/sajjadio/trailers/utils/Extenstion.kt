package com.sajjadio.trailers.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.media.MediaScannerConnection
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Environment
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.core.view.WindowCompat
import androidx.core.view.isVisible
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.snackbar.Snackbar
import com.sajjadio.trailers.R
import java.io.File
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException
import java.util.*


fun language() = if (Locale.getDefault().displayLanguage == "English") "en" else "ar"


@SuppressLint("UseCompatLoadingForDrawables")
fun ImageView.loadImage(url: String, imageSize: String?) {
    imageSize?.let {
        Glide.with(this)
            .load(Constant.IMAGE_PATH + imageSize + url)
            .into(this)
    }
}

fun Activity.setAsActionBar(toolbar: Toolbar, isBack: Boolean = true, title: String? = null) {
    (this as AppCompatActivity).setSupportActionBar(toolbar)
    toolbar.title = title
    if (isBack) {
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationOnClickListener { onBackPressed() }
    }
}


private var stateActive = false
fun View.setSnackbar(state: Int) {
    if (stateActive) {
        Snackbar.make(this, resources.getString(state), Snackbar.LENGTH_LONG)
            .setBackgroundTint(resources.getColor(R.color.green)).show()
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

@SuppressLint("CommitPrefEdits")
fun Context.setThemes(theme: Int): Int {
    val pref = this.getSharedPreferences(Constant.PREFERENCE_NAME, Context.MODE_PRIVATE)
    val editor = pref.edit()
    editor.putInt(Constant.THEME_APP, theme)
    editor.apply()

    return pref.getInt(Constant.THEME_APP, 0)
}

fun View.visibility(isVisible: Boolean?) {
    this.isVisible = isVisible != true
}

fun Int.isConnection() = when (this) {
    R.string.connected -> true
    R.string.noConnection -> false
    else -> false
}

fun Window.changeStatusBarColor(color: Int, show: Boolean = true) {
    WindowCompat.setDecorFitsSystemWindows(this, show)
    statusBarColor = ContextCompat.getColor(context, color)
}


fun NavDirections.movieToDestination(view: View?) {
    view?.findNavController()?.navigate(this)
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
            //for other device how are able to connect with Ethernet
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            //for check internet over Bluetooth
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
    val downloadButton = dialog.findViewById<ImageView>(R.id.imageButtonBack)
    downloadButton.setOnClickListener {
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
    largeImage.loadImage(imageUrl, imageSize)
    dialog.show()
}
