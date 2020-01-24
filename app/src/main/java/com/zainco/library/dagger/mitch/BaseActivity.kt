package com.zainco.library.dagger.mitch

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.zainco.library.dagger.mitch.ui.auth.AuthActivity
import com.zainco.library.dagger.mitch.ui.auth.AuthResource
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {
    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObservers()
    }

    fun subscribeObservers() {
        sessionManager.getAuthUser().observe(this, Observer { authResource ->
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
                        navigateLoginScreen()
                    }
                }
            }
        })
    }

    private fun navigateLoginScreen() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }
}