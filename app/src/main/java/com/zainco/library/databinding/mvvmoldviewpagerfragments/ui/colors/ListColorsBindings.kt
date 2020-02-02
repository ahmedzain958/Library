package com.zainco.library.databinding.mvvmoldviewpagerfragments.ui.colors

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zainco.library.databinding.mvvmoldviewpagerfragments.domain.model.Color

/**
 * @author  Wellington Costa on 31/12/2017.
 */
object ListColorsBindings {

    @JvmStatic
    @BindingAdapter("load_colors")
    fun loadColors(recyclerView: RecyclerView, colors: List<Color>?) {
        recyclerView.adapter =
            if (colors != null) ListColorsAdapter(colors) else ListColorsAdapter(emptyList())
    }

    @JvmStatic
    @BindingAdapter("load_color_value")
    fun loadColorValue(imageView: ImageView, color: Color?) {
        imageView.setBackgroundColor(android.graphics.Color.parseColor(color?.color))
    }

}