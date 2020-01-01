package com.zainco.library.databinding.ex2.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.internal.ViewUtils.dpToPx
import com.zainco.library.R
import com.zainco.library.databinding.ex1.MyClickHandlers
import com.zainco.library.databinding.ex2.model.Post
import com.zainco.library.databinding.ex2.utils.GridSpacingItemDecoration


class ProfileActivity : AppCompatActivity() ,PostsAdapter.PostsAdapterListener{
    lateinit var binding: ViewDataBinding
    lateinit var handlers: MyClickHandlers
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView<ViewDataBinding>(this, R.layout.activity_main)

        val toolbar: Toolbar = binding.toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(R.string.toolbar_profile)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        handlers = MyClickHandlers()

        renderProfile()

        initRecyclerView()
    }

    private fun initRecyclerView() {
        recyclerView = binding.content.recyclerView
        recyclerView.setLayoutManager(GridLayoutManager(this, 3))
        recyclerView.addItemDecoration(
            GridSpacingItemDecoration(
                3,
                dpToPx(4).toInt(),
                true
            )
        )
        recyclerView.setItemAnimator(DefaultItemAnimator())
        recyclerView.setNestedScrollingEnabled(false)
        mAdapter = PostsAdapter(getPosts()!!, this)
        recyclerView.setAdapter(mAdapter)
    }

    /**
     * Renders user profile data
     */
    private fun renderProfile() {
        user = User()
        user.setName("David Attenborough")
        user.setEmail("david@natgeo.com")
        user.setProfileImage("https://api.androidhive.info/images/nature/david.jpg")
        user.setAbout("Naturalist")
        // ObservableField doesn't have setter method, instead will
// be called using set() method
        user.numberOfPosts.set(3400L)
        user.numberOfFollowers.set(3050890L)
        user.numberOfFollowing.set(150L)
        // display user
        binding.setUser(user)
        // assign click handlers
        binding.content.setHandlers(handlers)
    }

    private fun getPosts(): ArrayList<Post>? {
        val posts: ArrayList<Post> = ArrayList()
        for (i in 1..9) {
            val post = Post()
            post.setImageUrl("https://api.androidhive.info/images/nature/$i.jpg")
            posts.add(post)
        }
        return posts
    }

    fun onPostClicked(post: Post) {
        Toast.makeText(
            applicationContext,
            "Post clicked! " + post.imageUrl,
            Toast.LENGTH_SHORT
        ).show()
    }
}
