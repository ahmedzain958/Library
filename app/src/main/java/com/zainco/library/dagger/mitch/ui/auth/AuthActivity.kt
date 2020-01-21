package com.zainco.library.dagger.mitch.ui.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.zainco.library.R
import com.zainco.library.dagger.mitch.ui.main.MitchDaggerMainActivity
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
        login_button.setOnClickListener {
            attemptLogin()
        }
        observeAuthState()
    }

    private fun observeAuthState() {
        viewModel.observeAuthState().observe(this, Observer { authResource ->
            //listening to any change happens in the mediatorlivedata
            if (authResource != null) {
                when (authResource.status) {
                    AuthResource.AuthStatus.LOADING -> {

                    }
                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        Toast.makeText(
                            this,
                            "AUTHENTICATED",
                            Toast.LENGTH_SHORT
                        ).show()
                        onLoginSuccess()
                    }
                    AuthResource.AuthStatus.ERROR -> {

                        Toast.makeText(
                            this,
                            "Error",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    AuthResource.AuthStatus.NOT_AUTHENTICATED -> {

                        Toast.makeText(
                            this,
                            "NOT_AUTHENTICATED",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
            }
        })
    }

    private fun attemptLogin() {
        if (user_id_input.text.toString().isEmpty().not()) {
            viewModel.authenticateWithId(user_id_input.text.toString().toInt())
        }
    }

    fun onLoginSuccess() {
        val intent = Intent(this, MitchDaggerMainActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun setLogo() {
        requestManager.load(logo).into(login_logo)
    }
}
