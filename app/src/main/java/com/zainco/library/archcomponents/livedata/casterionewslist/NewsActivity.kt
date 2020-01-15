package com.zainco.library.archcomponents.livedata.casterionewslist

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.zainco.library.R
import kotlinx.android.synthetic.main.activity_news.*

class NewsActivity : AppCompatActivity() {

    private val newsViewModel by lazy { ViewModelProviders.of(this).get(NewsViewModel::class.java) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_news)

        // Setting up RecyclerView
        val adapter = NewsAdapter()
        news_list.layoutManager = LinearLayoutManager(this)
        news_list.adapter = adapter

        newsViewModel.getNewsArticles().observe(this, Observer {
            it?.let {
                adapter.swapArticles(it)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.items_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.refresh -> {
                newsViewModel.updateNewsArticles()
            }
        }
        return super.onOptionsItemSelected(item)
    }
}