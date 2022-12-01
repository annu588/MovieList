package com.app.moviedemo.data.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.app.moviedemo.R
import com.bumptech.glide.Glide

data class Search(
    val Poster: String,
    val Title: String,
    val Type: String,
    val Year: String,
    val imdbID: String
)
@BindingAdapter("loadImage")
fun loadImage(imageView: ImageView, url: String?) {


    /*   imageView.load(url) {
           placeholder(R.drawable.ic_placeholder)
           error(R.drawable.ic_placeholder)
               //   crossfade(true) // crossfade from image_placeholder to thumbnail if it loads from disk
       }*/

    Glide.with(imageView.context).load(url).placeholder(R.drawable.ic_launcher_background).error(R.drawable.ic_launcher_background).into(imageView)

}