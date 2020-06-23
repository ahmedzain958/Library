package com.zainco.library.databinding.ex2.view

import android.content.res.Resources
import android.os.Bundle
import android.util.TypedValue
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.zainco.library.R
import com.zainco.library.databinding.ActivityProfileBinding
import com.zainco.library.databinding.ex2.model.Post
import com.zainco.library.databinding.ex2.model.User
import com.zainco.library.databinding.ex2.utils.GridSpacingItemDecoration


class ProfileActivity : AppCompatActivity(), PostsAdapter.PostsAdapterListener {
    lateinit var binding: ActivityProfileBinding
    lateinit var handlers: MyClickHandlers
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ActivityProfileBinding>(
            this,
            R.layout.activity_profile
        )
        //Android data binding generates a Binding class  holds all the bindings from the layout properties
        val toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(R.string.toolbar_profile)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        handlers = MyClickHandlers(this)

        renderProfile()

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val recyclerView = binding.content.recyclerView
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.addItemDecoration(
            GridSpacingItemDecoration(
                3,
                dpToPx(4).toInt(),
                true
            )
        )
        recyclerView.itemAnimator = DefaultItemAnimator()
        recyclerView.isNestedScrollingEnabled = false
        val mAdapter = PostsAdapter(getPosts()!!, this)
        recyclerView.adapter = mAdapter
    }

    /**
     * Renders user profile data
     */
    private fun renderProfile() {
        val user = User(
            "David Attenborough",
            "david@natgeo.com",
            "https://api.androidhive.info/images/nature/david.jpg",
            "Naturalist"
        )

        // ObservableField doesn't have setter method, instead will
// be called using set() method
        user.numberOfPosts.set(3400L)
        user.numberOfFollowers.set(3050890L)
        user.numberOfFollowing.set(150L)
        // display user
        binding.user = user
        // assign click handlers
        binding.content.handlers = handlers
    }

    private fun getPosts(): ArrayList<Post>? {
        val posts: ArrayList<Post> = ArrayList()
        val indices: IntRange = 1..9
        for (i in indices) {
            val post = Post("https://api.androidhive.info/images/nature/$i.jpg")
            posts.add(post)
        }
        return posts
    }

    override fun onPostClicked(post: Post?) {
        Toast.makeText(
            applicationContext,
            "Post clicked! " + post?.imageUrl,
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun dpToPx(dp: Int): Int {
        val r: Resources = resources
        return Math.round(
            TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                dp.toFloat(),
                r.displayMetrics
            )
        )
    }
}
