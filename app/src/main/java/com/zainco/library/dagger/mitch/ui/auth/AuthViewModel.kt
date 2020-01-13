package com.zainco.library.dagger.mitch.ui.auth

import androidx.lifecycle.ViewModel
import com.zainco.library.dagger.mitch.network.auth.AuthApi
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    var authApi: AuthApi/*, var sessionManager: SessionManager*/

) : ViewModel() {

}
