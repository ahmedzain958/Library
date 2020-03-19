package com.zainco.library.callrecord;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import com.zainco.library.R;

public class CallRecordActivity extends AppCompatActivity {

    ToggleButton toggleButton;
    TextView textSubHeader;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_record);
        textSubHeader = (TextView) findViewById(R.id.textSubHeader);
//        File CallRecorder = new File("/sdcard/CallRecorder");
//        CallRecorder.mkdirs();
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Runtime permission
        try {

            boolean permissionGranted_OutgoingCalls = ActivityCompat.checkSelfPermission(this, Manifest.permission.PROCESS_OUTGOING_CALLS) == PackageManager.PERMISSION_GRANTED;
            boolean permissionGranted_phoneState = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED;
            boolean permissionGranted_recordAudio = ActivityCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED;
            boolean permissionGranted_WriteExternal = ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
            boolean permissionGranted_ReadExternal = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;


            if (permissionGranted_OutgoingCalls) {

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.PROCESS_OUTGOING_CALLS}, 600);
            }
            if (permissionGranted_phoneState) {

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_PHONE_STATE}, 500);
            }
            if (permissionGranted_recordAudio) {

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO}, 400);
            }
            if (permissionGranted_WriteExternal) {

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 300);
            }
            if (permissionGranted_ReadExternal) {

            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 200);
            }
        } catch (
                Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("ResourceAsColor")
    public void OnButtonClick(View view) {
        try {
            Intent intent = new Intent(this, RecordingService.class);
            startService(intent);
            Toast.makeText(getApplicationContext(), "Call Recording is set ON", Toast.LENGTH_SHORT).show();
            textSubHeader.setText("Switch on Toggle to record your calls");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint("ResourceAsColor")
    public void OffButtonClick(View view) {
        try {

            Intent intent = new Intent(this, RecordingService.class);
            stopService(intent);
            Toast.makeText(getApplicationContext(), "Call Recording is set OFF", Toast.LENGTH_SHORT).show();
            textSubHeader.setText("Switch Off Toggle to stop recording your calls");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //                <uses-permission android:name="android.permission.PROCESS_OUTGOING_CALLS"></uses-permission>
//    <uses-permission android:name="android.permission.READ_PHONE_STATE"></uses-permission>
//    <uses-permission android:name="android.permission.RECORD_AUDIO"></uses-permission>
//    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE"></uses-permission>
//    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE"></uses-permission>

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == 200 || requestCode == 300 || requestCode == 400 || requestCode == 500 || requestCode == 600) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                try {
                    toggleButton.setVisibility(View.VISIBLE);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }


}
