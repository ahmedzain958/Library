package com.zainco.library.permissions.internalexternalstorage

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.zainco.library.R
import kotlinx.android.synthetic.main.activity_internal_external_permissions.*

class InternalExternalPermissionsActivity : AppCompatActivity() {
    private val REQUEST_CODE_PERMISSIONS = 102

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_internal_external_permissions)
        buttonRead.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this, Manifest.permission.READ_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(this, "READ_EXTERNAL_STORAGE", Toast.LENGTH_SHORT).show()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                    REQUEST_CODE_PERMISSIONS
                )
            }
        }
        buttonWrite.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    this, Manifest.permission.WRITE_EXTERNAL_STORAGE
                ) == PackageManager.PERMISSION_GRANTED
            ) {
                Toast.makeText(this, "WRITE_EXTERNAL_STORAGE", Toast.LENGTH_SHORT).show()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                    REQUEST_CODE_PERMISSIONS
                )
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//both permissions READ_EXTERNAL_STORAGE && WRITE_EXTERNAL_STORAGE have the same dialog permission

    }
}
