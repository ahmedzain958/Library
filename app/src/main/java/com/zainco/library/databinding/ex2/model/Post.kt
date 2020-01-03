package com.zainco.library.databinding.ex2.model

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide


class Post(val imageUrl: String) {
    companion object {
        @JvmStatic
        @BindingAdapter("bind:imageUrl")
    fun loadImage(view: ImageView, imageUrl: String?) {
        Glide.with(view.context)
            .load(imageUrl)
            .into(view)
    }
    }

}