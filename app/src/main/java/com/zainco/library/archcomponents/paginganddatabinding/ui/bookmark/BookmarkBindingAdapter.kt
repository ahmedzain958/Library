package com.zainco.library.archcomponents.paginganddatabinding.ui.bookmark

import androidx.databinding.BindingAdapter
import androidx.paging.PagedList
import androidx.recyclerview.widget.RecyclerView
import com.zainco.library.archcomponents.paginganddatabinding.data.db.entity.Bookmark

/**
 * @author Leopold
 */

@BindingAdapter(value = ["bookmarks", "viewModel"])
fun setBookmarks(view: RecyclerView, items: PagedList<Bookmark>?, vm: BookmarkViewModel) {
    view.adapter?.run {
        if (this is BookmarkAdapter) this.submitList(items)
    } ?: run {
        BookmarkAdapter(vm).apply {
            view.adapter = this
            this.submitList(items)
        }
    }
}