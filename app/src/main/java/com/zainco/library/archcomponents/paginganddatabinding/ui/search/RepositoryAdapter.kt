package com.zainco.library.archcomponents.paginganddatabinding.ui.search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.zainco.library.R
import com.zainco.library.archcomponents.paginganddatabinding.data.remote.domain.Repository
import com.zainco.library.archcomponents.paginganddatabinding.ui.BindingViewHolder
import com.zainco.library.databinding.ItemRepositoryBinding

/**
 * @author Leopold
 */
class RepositoryAdapter(var items: List<Repository> = arrayListOf(), val vm: SearchViewModel) :
    RecyclerView.Adapter<RepositoryAdapter.RepositoryViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RepositoryViewHolder {
        return RepositoryViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_repository,
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RepositoryViewHolder, position: Int) {
        holder.binding.item = items[position]
        holder.binding.vm = vm
    }

    override fun getItemCount() = items.size
    class RepositoryViewHolder(view: View) : BindingViewHolder<ItemRepositoryBinding>(view)
}