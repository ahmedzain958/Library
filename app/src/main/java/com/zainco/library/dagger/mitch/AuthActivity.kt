package com.zainco.library.dagger.mitch

import android.graphics.drawable.Drawable
import android.os.Bundle
import com.bumptech.glide.RequestManager
import com.zainco.library.R
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var logo: Drawable // uses the injected @Drawable class
    @Inject
    lateinit var requestManager: RequestManager  // uses the injected @RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        setLogo()

    }

    private fun setLogo() {
        requestManager.load(logo).into(login_logo)
    }
}
