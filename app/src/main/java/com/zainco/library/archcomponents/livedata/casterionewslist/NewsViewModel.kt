package com.zainco.library.archcomponents.livedata.casterionewslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class NewsViewModel : ViewModel() {

    var newsList: MutableLiveData<List<NewsArticle>> = MutableLiveData()

    init {
        newsList.value = DummyDataProvider.getDummyNews()
    }

    fun getNewsArticles() = newsList

    fun updateNewsArticles() {
        newsList.value = DummyDataProvider.getAnotherDummyNews()
    }
}