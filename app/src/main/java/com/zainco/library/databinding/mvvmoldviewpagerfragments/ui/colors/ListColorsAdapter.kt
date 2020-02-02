package com.zainco.library.databinding.mvvmoldviewpagerfragments.ui.colors

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zainco.library.R
import com.zainco.library.databinding.mvvmoldviewpagerfragments.domain.model.Color
import com.zainco.library.databinding.mvvmoldviewpagerfragments.ui.base.BaseAdapter

/**
 * @author Wellington Costa on 31/12/2017.
 */
class ListColorsAdapter constructor(list: List<Color>) : BaseAdapter<Color>(list) {

    override fun onCreateViewHolderBase(
        parent: ViewGroup?,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return ListColorsViewHolder(
            LayoutInflater
                .from(parent?.context)
                .inflate(R.layout.list_colors_item, parent, false)
        )
    }

    override fun onBindViewHolderBase(holder: RecyclerView.ViewHolder?, position: Int) {
        val binding = (holder as ListColorsViewHolder).binding
        val color = list[position]
        binding?.color = color
    }

}