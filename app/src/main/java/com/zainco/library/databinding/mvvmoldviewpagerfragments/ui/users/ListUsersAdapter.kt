package com.zainco.library.databinding.mvvmoldviewpagerfragments.ui.users


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zainco.library.R

import com.zainco.library.databinding.mvvmoldviewpagerfragments.domain.model.User
import com.zainco.library.databinding.mvvmoldviewpagerfragments.ui.base.BaseAdapter

/**
 * @author Wellington Costa on 31/12/2017.
 */
class ListUsersAdapter constructor(list: List<User>) : BaseAdapter<User>(list) {

    override fun onCreateViewHolderBase(
        parent: ViewGroup?,
        viewType: Int
    ): RecyclerView.ViewHolder {
        return ListUsersViewHolder(
            LayoutInflater
                .from(parent?.context)
                .inflate(R.layout.list_users_item, parent, false)
        )
    }

    override fun onBindViewHolderBase(holder: RecyclerView.ViewHolder?, position: Int) {
        val binding = (holder as ListUsersViewHolder).binding
        val user = list[position]
        binding?.user = user
    }

}