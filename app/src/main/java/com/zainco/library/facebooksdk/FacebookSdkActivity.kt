package com.zainco.library.facebooksdk

import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.facebook.*
import com.facebook.login.LoginResult
import com.zainco.library.R
import kotlinx.android.synthetic.main.activity_facebook_sdk.*
import org.json.JSONObject
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class FacebookSdkActivity : AppCompatActivity() {
    lateinit var callback: CallbackManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facebook_sdk)
        try {
            val info = packageManager.getPackageInfo(
                packageName,
                PackageManager.GET_SIGNATURES
            )
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }
        } catch (e: PackageManager.NameNotFoundException) {
        } catch (e: NoSuchAlgorithmException) {
        }


        callback = CallbackManager.Factory.create()
        login_button.setReadPermissions(listOf<String>("email", "public_profile"))
        login_button.registerCallback(callback, object : FacebookCallback<LoginResult> {
            override fun onSuccess(result: LoginResult?) {
                Toast.makeText(this@FacebookSdkActivity, "succeeded", Toast.LENGTH_SHORT)
                    .show()
            }

            override fun onCancel() {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onError(error: FacebookException?) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

        })

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        callback.onActivityResult(requestCode, resultCode, data)
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun loadUserProfile(accesToken: AccessToken) {
        val request =
            GraphRequest.newMeRequest(accesToken, object : GraphRequest.GraphJSONObjectCallback {
                override fun onCompleted(oo: JSONObject?, response: GraphResponse?) {
                    try {
                        textView4.setText(oo?.getString("first_name"))
                    } catch (e: ExceptionInInitializerError) {

                    }
                }
            })
        val bundle = Bundle()
        bundle.putString("fields", "first_name,last_name,email,id")
        request.parameters = bundle
        request.executeAsync()
    }

    val tokenTracker = object : AccessTokenTracker() {
        override fun onCurrentAccessTokenChanged(
            oldAccessToken: AccessToken?,
            currentAccessToken: AccessToken?
        ) {
            if (currentAccessToken == null) {
                Toast.makeText(this@FacebookSdkActivity, "no user", Toast.LENGTH_SHORT)
                    .show()
            } else {
                loadUserProfile(currentAccessToken)
            }
        }
    }
}
