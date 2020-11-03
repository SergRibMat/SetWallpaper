package com.example.setwallpaper

import android.app.Activity
import android.app.WallpaperManager
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.coroutines.*


const val REQUEST_IMAGE_GET = 101

class MainActivity : AppCompatActivity() {


    var myUri: String? = null
    private var viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.IO + viewModelJob)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setValueToResolutionTv(resolutionString())

        findViewById<Button>(R.id.set_wallapaper_btn).setOnClickListener {
            setWallpaperMethod()
        }

        findViewById<Button>(R.id.select_image_btn).setOnClickListener {
            selectImage()
        }
        /*
        * The process to change the device wallpaper is
        *1- take the method setWallpaperMethod
        * 2- You will need to run the action on background (a coroutine)
        * 3- ask for SET_WALLPAPER permission
        * */
    }

    private fun setWallpaperMethod() {//call this method on a background thread
        if (!myUri.isNullOrEmpty()) {
            uiScope.launch {
                val wallpaperManager: WallpaperManager = WallpaperManager.getInstance(applicationContext)
                if (wallpaperManager.isSetWallpaperAllowed) {
                    wallpaperManager.setBitmap(getImageWithGlide())
                } else {
                    showToast("No permission")
                }
            }
        }else{
            showToast("Select an image")
        }

    }

    private fun selectImage() {
        val intent = createIntent(Intent.ACTION_GET_CONTENT, "image/*")
        startActivityForResult(intent, REQUEST_IMAGE_GET)
    }

    private fun createIntent(action: String, typeString: String): Intent {
        return Intent(action).apply {
            type = typeString
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_GET && resultCode == Activity.RESULT_OK) {
            uiScope.launch {
                withContext(Dispatchers.Main) {
                    oneOrMultiple(data)
                }
            }
        }
    }

    fun oneOrMultiple(data: Intent?) {
        if (data != null) {
            printImageWithGlide(data.dataString!!)
            myUri = data.dataString!!
        }
    }

    private fun printImageWithGlide(data: String?) {
        Glide.with(this)
            .load(data)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(findViewById(R.id.show_picture_imageview))
    }

    private fun getImageWithGlide() = Glide.with(this)
        .asBitmap()
        .load(myUri)
        .submit()
        .get()


    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }


    fun resolutionString(): String {
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        var width = displayMetrics.widthPixels
        var height = displayMetrics.heightPixels
        return "$width x $height"
    }

    fun setValueToResolutionTv(text: String) {
        val textView = findViewById<TextView>(R.id.resolution_tv)
        textView.text = text
    }
}