package com.zainco.library.databinding.ex2.model

import android.widget.ImageView
import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import androidx.databinding.BindingAdapter
import androidx.databinding.ObservableField
import androidx.databinding.library.baseAdapters.BR
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions


class User(
    private val _name: String,
    private val _email: String,
    private val _profileImage: String,
    private val _about: String
) : BaseObservable() {
    var numberOfFollowers = ObservableField<Long>()
    var numberOfPosts = ObservableField<Long>()
    var numberOfFollowing = ObservableField<Long>()


    var name: String = ""
        @Bindable get() = _name
        set(value) {
            field = value
            notifyPropertyChanged(BR.name)
        }
    var email: String = ""
        @Bindable get() = _email
        set(value) {
            field = value
            notifyPropertyChanged(BR.email)
        }
    var about: String = ""
        @Bindable get() = _about
        set(value) {
            field = value
            notifyPropertyChanged(BR.about)
        }
    var profileImage: String = ""
        @Bindable get() = _profileImage
        set(value) {
            field = value
            notifyPropertyChanged(BR.profileImage)
        }

    @BindingAdapter("profileImage")
    fun loadImage(view: ImageView, imageUrl: String?) {
        Glide.with(view.getContext())
            .load(imageUrl)
            .apply(RequestOptions.circleCropTransform())
            .into(view)
        // If you consider Picasso, follow the below
// Picasso.with(view.getContext()).load(imageUrl).placeholder(R.drawable.placeholder).into(view);
    }
}