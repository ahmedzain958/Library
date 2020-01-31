package com.zainco.library.archcomponents.paginganddatabinding.ui.search

import com.zainco.library.archcomponents.paginganddatabinding.core.BaseViewModel
import com.zainco.library.archcomponents.paginganddatabinding.data.db.dao.BookmarkDao
import com.zainco.library.archcomponents.paginganddatabinding.data.db.entity.Bookmark
import com.zainco.library.archcomponents.paginganddatabinding.data.remote.api.SearchAPI
import com.zainco.library.archcomponents.paginganddatabinding.data.remote.domain.Repository
import com.zainco.library.archcomponents.paginganddatabinding.util.NotNullMutableLiveData
import com.zainco.library.archcomponents.paginganddatabinding.util.ioThread
import com.zainco.library.archcomponents.paginganddatabinding.extension.with

/**
 * @author Leopold
 */
class SearchViewModel(private val api: SearchAPI, private val dao: BookmarkDao) : BaseViewModel() {
    private var query: String = ""
        get() = if (field.isEmpty()) "MVVM" else field

    private val _refreshing: NotNullMutableLiveData<Boolean> = NotNullMutableLiveData(false)
    val refreshing: NotNullMutableLiveData<Boolean>
        get() = _refreshing

    private val _items: NotNullMutableLiveData<List<Repository>> = NotNullMutableLiveData(arrayListOf())
    val items: NotNullMutableLiveData<List<Repository>>
        get() = _items

    fun doSearch() {
        val params = mutableMapOf<String, String>().apply {
            this["q"] = query
            this["sort"] = "stars"
        }

        addToDisposable(api.search(params).with()
            .doOnSubscribe { _refreshing.value = true }
            .doOnSuccess { _refreshing.value = false }
            .doOnError { _refreshing.value = false }
            .subscribe({
                _items.value = it.repositories
            }, {
                // handle errors
            }))
    }

    fun onQueryChange(query: CharSequence) {
        this.query = query.toString()
    }

    fun saveToBookmark(repository: Repository) = ioThread { dao.insert(Bookmark.to(repository)) }
}