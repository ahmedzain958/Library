package com.zainco.library.databinding.mvvmoldviewpagerfragments.ui

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.zainco.library.R
import com.zainco.library.databinding.ActivityFragmentsviewpagerBinding
import com.zainco.library.databinding.mvvmoldviewpagerfragments.ui.colors.ListColorsFragment
import com.zainco.library.databinding.mvvmoldviewpagerfragments.ui.common.ViewPagerAdapter
import com.zainco.library.databinding.mvvmoldviewpagerfragments.ui.users.ListUsersFragment
import dagger.android.support.DaggerAppCompatActivity

/**
 * @author Wellington Costa on 31/12/2017.
 */
class FragmentsViewPagerActivity : DaggerAppCompatActivity() {

    private lateinit var binding: ActivityFragmentsviewpagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViews()
    }

    private fun setupViews() {
        binding = DataBindingUtil.setContentView(this, R.layout.activity_fragmentsviewpager)
        setupToolbar()
        setupTabs()
    }

    private fun setupToolbar() {
        setSupportActionBar(binding.includeToolbar.toolbar)
        supportActionBar?.title = getString(R.string.app_name)
    }

    private fun setupTabs() {
        val adapter = ViewPagerAdapter(supportFragmentManager)

        adapter.addFragment(ListUsersFragment(), getString(R.string.users))
        adapter.addFragment(ListColorsFragment(), getString(R.string.colors))

        binding.viewPager.adapter = adapter
//        binding.includeToolbar?.tabs?.setupWithViewPager(binding.viewPager)
    }

}