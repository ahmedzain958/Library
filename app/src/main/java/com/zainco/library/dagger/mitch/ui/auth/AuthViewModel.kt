package com.zainco.library.dagger.mitch.ui.auth

import androidx.lifecycle.ViewModel
import com.google.firebase.udacity.daggerPosts.dagger.network.auth.AuthApi
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    var authApi: AuthApi/*, var sessionManager: SessionManager*/

) : ViewModel() {}