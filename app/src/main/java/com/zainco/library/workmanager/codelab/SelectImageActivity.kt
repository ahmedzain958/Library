package com.zainco.library.workmanager.codelab

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.zainco.library.R
import kotlinx.android.synthetic.main.activity_select_image.*

class SelectImageActivity : AppCompatActivity() {
    private val TAG = "SelectImageActivity"
    private val sPermissions =
        listOf(
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
        )
    private var mPermissionRequestCount = 0
    private val MAX_NUMBER_REQUEST_PERMISSIONS = 2
    private val REQUEST_CODE_IMAGE = 100
    private val REQUEST_CODE_PERMISSIONS = 101

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_image)
        requestPermissionsIfNecessary()
        selectImage.setOnClickListener {
            val chooseIntent = Intent(
                Intent.ACTION_PICK,
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            )
            startActivityForResult(chooseIntent, REQUEST_CODE_IMAGE)
        }
    }

    private fun requestPermissionsIfNecessary() {
        if (!checkAllPermissions()) {
            if (mPermissionRequestCount < MAX_NUMBER_REQUEST_PERMISSIONS) {
                mPermissionRequestCount += 1
                ActivityCompat.requestPermissions(
                    this,
                    sPermissions.toTypedArray(),
                    REQUEST_CODE_PERMISSIONS
                )
            } else {
                Toast.makeText(
                    this, R.string.set_permissions_in_settings,
                    Toast.LENGTH_LONG
                ).show()
                findViewById<View>(R.id.selectImage).isEnabled = false
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            requestPermissionsIfNecessary() // no-op if permissions are granted already.
        }
    }

    private fun handleImageRequestResult(data: Intent) {
        var imageUri: Uri? = null
        if (data.clipData != null) {//I think this is for multiselsct
            imageUri = data.clipData!!.getItemAt(0).uri
        } else if (data.data != null) {
            imageUri = data.data
        }

        if (imageUri == null) {
            Log.e(TAG, "Invalid input image Uri.")
            return
        }

        val filterIntent = Intent(this, WorkManagerBlurrActivity::class.java)
        filterIntent.putExtra(Constants.KEY_IMAGE_URI, imageUri.toString())
        startActivity(filterIntent)
    }

    private fun checkAllPermissions(): Boolean {
        var hasPermissions = true
        for (permission in sPermissions) {
            hasPermissions = hasPermissions and (ContextCompat.checkSelfPermission(
                this, permission
            ) == PackageManager.PERMISSION_GRANTED)
        }
        return hasPermissions
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            when (requestCode) {
                REQUEST_CODE_IMAGE -> handleImageRequestResult(data!!)
                else -> Log.d(TAG, "Unknown request code.")
            }
        } else {
            Log.e(
                TAG,
                String.format("Unexpected Result code %s", resultCode)
            )
        }
    }


}
