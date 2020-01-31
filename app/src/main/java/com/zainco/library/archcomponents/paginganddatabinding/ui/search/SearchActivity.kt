package com.zainco.library.archcomponents.paginganddatabinding.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.LayoutRes
import com.zainco.library.R
import com.zainco.library.archcomponents.paginganddatabinding.ui.BindingActivity
import com.zainco.library.archcomponents.paginganddatabinding.ui.bookmark.BookmarkActivity
import com.zainco.library.databinding.ActivitySearchBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author Leopold
 */
class SearchActivity : BindingActivity<ActivitySearchBinding>() {
    @LayoutRes
    override fun getLayoutResId() = R.layout.activity_search
//    private val searchViewModel: SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.vm = getViewModel()
        binding.lifecycleOwner = this
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.bookmark -> {
                startActivity(Intent(this, BookmarkActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}