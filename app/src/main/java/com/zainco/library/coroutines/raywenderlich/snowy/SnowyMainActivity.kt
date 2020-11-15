package com.zainco.library.coroutines.raywenderlich.snowy

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.zainco.library.R
import com.zainco.library.coroutines.raywenderlich.snowy.model.Tutorial
import kotlinx.android.synthetic.main.activity_main_snowy.*

/**
 * Created by Ahmed Zain on 04/11/2020.
 */
class SnowyMainActivity : AppCompatActivity() {

    private var tutorialPagerAdapter: TutorialPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_snowy)
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.kotlin_title)))
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.android_name)))
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.rxkotlin_name)))
        tabLayout.addTab(tabLayout.newTab().setText(getString(R.string.kitura_name)))
        tutorialPagerAdapter = TutorialPagerAdapter(getTutorialData(), supportFragmentManager)
        viewPager.adapter = tutorialPagerAdapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }
        })
    }

    private fun getTutorialData(): List<Tutorial> {
        val tutorialList = arrayListOf<Tutorial>()
        tutorialList.add(
            Tutorial(
                getString(R.string.kotlin_title), getString(R.string.kotlin_url),
                getString(R.string.kotlin_desc)
            )
        )
        tutorialList.add(
            Tutorial(
                getString(R.string.android_name), getString(R.string.android_url),
                getString(R.string.android_desc)
            )
        )
        tutorialList.add(
            Tutorial(
                getString(R.string.rxkotlin_name), getString(R.string.rxkotlin_url),
                getString(R.string.rxkotlin_desc)
            )
        )
        tutorialList.add(
            Tutorial(
                getString(R.string.kitura_name), getString(R.string.kitura_url),
                getString(R.string.kitura_desc)
            )
        )
        return tutorialList
    }
}
