package com.zainco.library.databinding.mvvmoldviewpagerfragments.ui.users

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.facebook.drawee.generic.RoundingParams
import com.facebook.drawee.view.SimpleDraweeView
import com.zainco.library.databinding.mvvmoldviewpagerfragments.domain.model.User

/**
 * @author Wellington Costa on 31/12/2017.
 */
object ListUsersBindings {

    @JvmStatic
    @BindingAdapter("app:load_users")
    fun loadUsers(recyclerView: RecyclerView, users: List<User>) {
        recyclerView.adapter =
            if (users != null) ListUsersAdapter(users) else ListUsersAdapter(emptyList())
    }

    @JvmStatic
    @BindingAdapter("load_user_avatar")
    fun loadUserAvatar(simpleDraweeView: SimpleDraweeView, user: User?) {
        val roundingParams = RoundingParams.fromCornersRadius(5f)
        roundingParams.roundAsCircle = true

        simpleDraweeView.hierarchy.roundingParams = roundingParams
        simpleDraweeView.setImageURI(user?.avatar)
    }

}