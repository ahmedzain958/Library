package com.zainco.library.workmanager.socialmedia

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.zainco.library.R
import kotlinx.android.synthetic.main.activity_human_connector.*

class HumanConnectorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_human_connector)
        postsRecycler.apply {
            layoutManager = LinearLayoutManager(this@HumanConnectorActivity)
            adapter = PostsAdapter()
        }
    }
}
