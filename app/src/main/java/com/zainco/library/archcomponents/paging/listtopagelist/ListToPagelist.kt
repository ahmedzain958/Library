package com.zainco.library.archcomponents.paging.listtopagelist

import android.os.AsyncTask
import android.os.Handler
import android.os.Looper
import androidx.paging.PageKeyedDataSource
import androidx.paging.PagedList
import java.util.concurrent.Executor

/**
 * Created by Ahmed Zain on 7/15/2020.
 */
class ListToPagelist {
    private fun getConfig(dataSourceSize: Int): PagedList.Config {
        return PagedList.Config.Builder()
            .setInitialLoadSizeHint(dataSourceSize)
            .setPageSize(dataSourceSize)
            .build()
    }

    fun getPagedList(list: List<Any>): PagedList<Any> {
        val provider = ListProvider(list)
        val dataSource =
            DataSource(
                provider
            )
        val pagedList =
            PagedList.Builder(dataSource, getConfig(list.size))
                .setInitialKey(0)
                .setNotifyExecutor(UiThreadExecutor())
                .setFetchExecutor(AsyncTask.THREAD_POOL_EXECUTOR)
                .build()
        return pagedList
    }

    class ListProvider(private val list: List<Any>) {
        fun getObjectList(page: Int, pageSize: Int): List<Any> {
            val initialIndex = page * pageSize
            val finalIndex = initialIndex + pageSize
            return list.subList(initialIndex, finalIndex)
        }

    }

    class DataSource(private val provider: ListProvider) :
        PageKeyedDataSource<Int, Any>() {
        override fun loadInitial(
            params: LoadInitialParams<Int>,
            callback: LoadInitialCallback<Int, Any>
        ) {
            val result =
                provider.getObjectList(0, params.requestedLoadSize)
            callback.onResult(result, 1, 2)
        }

        override fun loadBefore(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, Any>
        ) {
            val result =
                provider.getObjectList(params.key, params.requestedLoadSize)
            var nextIndex: Int? = null
            if (params.key > 1) {
                nextIndex = params.key - 1
            }
            callback.onResult(result, nextIndex)
        }

        override fun loadAfter(
            params: LoadParams<Int>,
            callback: LoadCallback<Int, Any>
        ) {
            val result =
                provider.getObjectList(params.key, params.requestedLoadSize)
            callback.onResult(result, params.key + 1)
        }

    }

    class UiThreadExecutor : Executor {
        private val handler = Handler(Looper.getMainLooper())
        override fun execute(command: Runnable) {
            handler.post(command)
        }
    }
}