package com.zainco.library.audiorecord.edmt

import android.Manifest.permission.RECORD_AUDIO
import android.Manifest.permission.WRITE_EXTERNAL_STORAGE
import android.content.pm.PackageManager
import android.media.MediaPlayer
import android.media.MediaRecorder
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.zainco.library.R
import kotlinx.android.synthetic.main.activity_audio_record_e_d_m_t.*
import java.io.IOException
import java.util.*

class AudioRecordEDMTActivity : AppCompatActivity() {
    companion object {
        const val REQUEST_PERMISSION_CODE = 1000
    }

    var mediaRecorder: MediaRecorder? = null
    var mediaPlayer: MediaPlayer? = null
    var pathSave = ""

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio_record_e_d_m_t)
        if (!checkPermissionFromDevice()) {
            requestPermissions()
        }
        buttonrecord.setOnClickListener {
            if (checkPermissionFromDevice()) {
                pathSave =
                    Environment.getExternalStorageDirectory().absolutePath + "/" + UUID.randomUUID()
                        .toString() + "_audiorecord.3gp"
                mediaRecorder = MediaRecorder()
                mediaRecorder?.setAudioSource(MediaRecorder.AudioSource.MIC)
                mediaRecorder?.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP)
                mediaRecorder?.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB)
                mediaRecorder?.setOutputFile(pathSave)
                try {
                    mediaRecorder?.prepare()
                    mediaRecorder?.start()
                    buttonPlayFIle.isEnabled = false
                    buttonstopFile.isEnabled = false
                    Toast.makeText(
                        applicationContext,
                        "recording",
                        Toast.LENGTH_SHORT
                    ).show()
                } catch (e: IOException) {

                }
            } else {
                requestPermissions()
            }
        }
        buttonstop.setOnClickListener {

            mediaRecorder?.stop()
            buttonstop.isEnabled = false
            buttonPlayFIle.isEnabled = true
            buttonrecord.isEnabled = true
            buttonstopFile.isEnabled = false
        }
        buttonPlayFIle.setOnClickListener {
            buttonstop.isEnabled = false
            buttonrecord.isEnabled = false
            buttonstopFile.isEnabled = true
            mediaPlayer = MediaPlayer()
            try {
                mediaPlayer?.let {
                    it.setDataSource(pathSave)
                    it.prepare()
                }
            } catch (e: IOException) {

            }
            mediaPlayer?.start()
            Toast.makeText(
                applicationContext,
                "palying",
                Toast.LENGTH_SHORT
            ).show()
        }
        buttonstopFile.setOnClickListener {
            buttonstop.isEnabled = false
            buttonrecord.isEnabled = true
            buttonstopFile.isEnabled = false
            buttonPlayFIle.isEnabled = true
            if (mediaPlayer != null) {
                mediaPlayer?.stop()
                mediaPlayer?.release()
            }
        }

    }

    private fun checkPermissionFromDevice(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            WRITE_EXTERNAL_STORAGE
        ) == PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(
                    this,
                    RECORD_AUDIO
                ) == PackageManager.PERMISSION_GRANTED
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(WRITE_EXTERNAL_STORAGE, RECORD_AUDIO),
            REQUEST_PERMISSION_CODE
        )
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_PERMISSION_CODE) {
            if (grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(
                    applicationContext,
                    "Gran",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                Toast.makeText(
                    applicationContext,
                    "not Gran",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}
