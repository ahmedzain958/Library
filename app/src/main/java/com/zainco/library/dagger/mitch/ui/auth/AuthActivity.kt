package com.zainco.library.dagger.mitch.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.zainco.library.R
import com.zainco.library.dagger.mitch.viewmodel.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var providerFactory: ViewModelProviderFactory // use it with viewModel to solve the problem with dagger
    private lateinit var viewModel: AuthViewModel

    @Inject
    lateinit var logo: Drawable // uses the injected @Drawable class
    @Inject
    lateinit var requestManager: RequestManager  // uses the injected @RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        viewModel = ViewModelProvider(this, providerFactory).get(AuthViewModel::class.java)
        setLogo()
    }

    private fun setLogo() {
        requestManager.load(logo).into(login_logo)
    }
}
