package com.zainco.library.databinding.mvvmoldviewpagerfragments.ui.users

import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.zainco.library.databinding.ListUsersItemBinding

/**
 * @author Wellington Costa on 31/12/2017.
 */
class ListUsersViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    val binding: ListUsersItemBinding? = DataBindingUtil.bind(view)

}