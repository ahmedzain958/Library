package com.zainco.library.permissions;

import android.Manifest;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.google.android.material.snackbar.Snackbar;
import com.zainco.library.R;

public class PermissionsActivity extends AppCompatActivity
        implements ActivityCompat.OnRequestPermissionsResultCallback {

    private static final int PERMISSION_REQUEST_CAMERA = 0;

    private View mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permissions);
        mLayout = findViewById(R.id.main_layout);

        // Register a listener for the 'Show Camera Preview' button.
        findViewById(R.id.button_open_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showCameraPreview();
            }
        });
        /////for testing meta data
        try {
            ApplicationInfo applicationInfo = getPackageManager().getApplicationInfo(getPackageName(), PackageManager.GET_META_DATA);
            Bundle bundle = applicationInfo.metaData;
            String value = bundle.getString("zain");
            Toast.makeText(
                    this,
                    value,
                    Toast.LENGTH_LONG
            ).show();

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }

    //if granted 5eer w baraka
    private void showCameraPreview() {
        // BEGIN_INCLUDE(startCamera)
        // Check if the Camera permission has been granted; it will be opened without anything
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)//only checks if permission was granted or not and doesn't display any dialogs
                == PackageManager.PERMISSION_GRANTED) {
            // Permission is already available, start camera preview
            Snackbar.make(mLayout,
                    R.string.camera_permission_available,
                    Snackbar.LENGTH_SHORT).show();
            startCamera();
        } else {//if not granted . either rationale or not
            /**
             * Requests the {@link android.Manifest.permission#CAMERA} permission.
             * If an additional rationale should be displayed, the user has to launch the request from
             * a SnackBar that includes additional information.
             */
            /* if permission dialog displayed before and user denied it, the next condition shouldShowRequestPermissionRationale returns true
             * we here should explain the user why this permission is required by ordinary dialog (or Snakbar as here) for ex with OK and after responding the permission dialog will be displayed
             * but this time dialog will be displayed with checkbox "never ask again" if user checked it and denied the next condition will always return false */
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
                // Provide an additional rationale to the user if the permission was not granted
                // and the user would benefit from additional context for the use of the permission.
                // Display a SnackBar with cda button to request the missing permission.
                Snackbar.make(mLayout, R.string.camera_access_required,
                        Snackbar.LENGTH_INDEFINITE).setAction(R.string.ok, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Request the permission
                        ActivityCompat.requestPermissions(PermissionsActivity.this,
                                new String[]{Manifest.permission.CAMERA},
                                PERMISSION_REQUEST_CAMERA);
                    }
                }).show();

            } else {
                //<b>Camera could not be opened.</b>\nThis occurs when the camera is not available (for example it is already in use) or if the system has denied access (for example when camera access has been disabled).
                Snackbar.make(mLayout, R.string.camera_unavailable, Snackbar.LENGTH_SHORT).show();
                // Request the permission. The result will be received in onRequestPermissionResult().
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
            }
        }
        // END_INCLUDE(startCamera)
    }

    private void startCamera() {
        Intent intent = new Intent(this, CameraPreviewActivity.class);
        startActivity(intent);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        // BEGIN_INCLUDE(onRequestPermissionsResult)
        if (requestCode == PERMISSION_REQUEST_CAMERA) {
            // Request for camera permission.
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission has been granted. Start camera preview Activity.
                Snackbar.make(mLayout, R.string.camera_permission_granted,
                        Snackbar.LENGTH_SHORT)
                        .show();
                startCamera();
            } else {
                // Permission request was denied (don't show again is checked too)
                Snackbar.make(mLayout, R.string.camera_permission_denied,
                        Snackbar.LENGTH_SHORT)
                        .show();
            }
        }
        // END_INCLUDE(onRequestPermissionsResult)
    }
}