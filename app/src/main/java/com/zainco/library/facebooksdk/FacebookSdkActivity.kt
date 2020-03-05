package com.zainco.library.facebooksdk

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Base64
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.zainco.library.R
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException

class FacebookSdkActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_facebook_sdk)
        try {
            val info = packageManager.getPackageInfo(
                "your package",
                PackageManager.GET_SIGNATURES)
            for (signature in info.signatures) {
                val md = MessageDigest.getInstance("SHA")
                md.update(signature.toByteArray())
                Log.e("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT))
            }
        } catch (e: PackageManager.NameNotFoundException) {
        } catch (e: NoSuchAlgorithmException) {
        }
//        printKeyHash()
    }

  /*  private fun printKeyHash() {
        val info = packageManager.getPackageInfo(packageName,PackageManager.GET_SIGNING_CERTIFICATES)
        info.sign
    }*/
}
