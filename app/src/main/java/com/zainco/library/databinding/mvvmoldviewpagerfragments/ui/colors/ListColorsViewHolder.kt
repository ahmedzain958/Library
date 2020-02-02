package com.zainco.library.databinding.mvvmoldviewpagerfragments.ui.colors

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.zainco.library.databinding.ListColorsItemBinding

/**
 * @author Wellington Costa on 31/12/2017.
 */
class ListColorsViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding: ListColorsItemBinding? = DataBindingUtil.bind(view)

}