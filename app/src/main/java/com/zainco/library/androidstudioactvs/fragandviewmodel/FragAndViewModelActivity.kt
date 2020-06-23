package com.zainco.library.androidstudioactvs.fragandviewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.zainco.library.androidstudioactvs.fragandviewmodel.ui.main.FragAndViewModelFragment

class FragAndViewModelActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.frag_and_view_model_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, FragAndViewModelFragment.newInstance())
                .commitNow()
        }
    }
}