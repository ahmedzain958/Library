package com.zainco.library.databinding.ex1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.zainco.library.R
import com.zainco.library.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var user: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        user = User("Ravi Tamada", "ravi@androidhive.info")
        binding.user = user
        binding.content.handlers = MyClickHandlers()
    }
}
