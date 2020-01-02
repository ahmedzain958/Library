package com.zainco.library.databinding.ex2.view

import android.content.Context
import android.view.View
import android.widget.Toast
import com.zainco.library.databinding.ex1.MainApplication
import com.zainco.library.databinding.ex2.model.User


class MyClickHandlers(var context: Context) {
    /**
     * Demonstrating updating bind data
     * Profile name, number of posts and profile image
     * will be updated on Fab click
     */
    fun onProfileFabClicked(view: View?) {
        val user = User(
            "Sir David Attenborough",
            "email",
            "https://api.androidhive.info/images/nature/david1.jpg",
            "about"
        )
        // updating ObservableField
        user.numberOfPosts.set(5500L)
        user.numberOfFollowers.set(5050890L)
        user.numberOfFollowing.set(180L)
    }

    fun onProfileImageLongPressed(view: View?): Boolean {
        Toast.makeText(
            MainApplication.applicationContext(),
            "Profile image long pressed!",
            Toast.LENGTH_LONG
        ).show()
        return false
    }

    fun onFollowersClicked(view: View?) {
        Toast.makeText(context, "Followers is clicked!", Toast.LENGTH_SHORT).show()
    }

    fun onFollowingClicked(view: View?) {
        Toast.makeText(context, "Following is clicked!", Toast.LENGTH_SHORT).show()
    }

    fun onPostsClicked(view: View?) {
        Toast.makeText(context, "Posts is clicked!", Toast.LENGTH_SHORT).show()
    }

}