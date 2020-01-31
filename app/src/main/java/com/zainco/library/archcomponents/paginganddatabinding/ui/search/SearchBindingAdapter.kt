package com.zainco.library.archcomponents.paginganddatabinding.ui.search

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.zainco.library.archcomponents.paginganddatabinding.data.remote.domain.Repository

/**
 * @author Leopold
 */
@BindingAdapter(value = ["repositories", "viewModel"])
fun setRepositories(view: RecyclerView, items: List<Repository>, vm: SearchViewModel) {
    view.adapter?.run {
        if (this is RepositoryAdapter) {
            this.items = items
            this.notifyDataSetChanged()
        }
    } ?: run {
        RepositoryAdapter(items, vm).apply { view.adapter = this }
    }
}