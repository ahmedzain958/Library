package com.zainco.library.archcomponents.paginganddatabinding.ui.bookmark

import android.os.Bundle
import androidx.annotation.LayoutRes
import com.zainco.library.R
import com.zainco.library.archcomponents.paginganddatabinding.ui.BindingActivity
import com.zainco.library.databinding.ActivityBookmarkBinding
import org.koin.androidx.viewmodel.ext.android.getViewModel

/**
 * @author Leopold
 */
class BookmarkActivity : BindingActivity<ActivityBookmarkBinding>() {
    @LayoutRes
    override fun getLayoutResId() = R.layout.activity_bookmark

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.vm = getViewModel()
        binding.lifecycleOwner = this
    }
}