package com.zainco.library.archcomponents.paginganddatabinding.ui.bookmark

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.zainco.library.archcomponents.paginganddatabinding.core.BaseViewModel
import com.zainco.library.archcomponents.paginganddatabinding.data.db.dao.BookmarkDao
import com.zainco.library.archcomponents.paginganddatabinding.data.db.entity.Bookmark
import com.zainco.library.archcomponents.paginganddatabinding.util.ioThread

/**
 * @author Leopold
 */
class BookmarkViewModel(private val dao: BookmarkDao) : BaseViewModel() {
    val items: LiveData<PagedList<Bookmark>> =
        LivePagedListBuilder(dao.findAll(),  /* page size */ 10).build()

    init {
        Log.d("items", items.toString())
    }

    fun delete(bookmark: Bookmark) = ioThread { dao.delete(bookmark) }
}