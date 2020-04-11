package com.zainco.library.salaty

import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import android.util.Log
import com.zainco.library.R

class AzanService : Service() {
    private lateinit var mediaPlayer: MediaPlayer

    companion object {
        const val ACTION_PLAY = "PLAY"
        const val ACTION_PAUSE = "PAUSE"
        const val ACTION_RESUME = "RESUME"
    }

    override fun onCreate() {
        super.onCreate()
        mediaPlayer = MediaPlayer.create(this, R.raw.adham)
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        if (intent?.action == ACTION_PLAY) {
            Log.d("AhmedZain", "start()")
            start()
        } else if (intent?.action == ACTION_PAUSE) {
            Log.d("AhmedZain", "pause()")
            pause()
        } else if (intent?.action == ACTION_RESUME) {
            Log.d("AhmedZain", "resume()")
            pause()
        }
        return START_REDELIVER_INTENT
    }

    override fun onBind(intent: Intent?): IBinder? {
        return null//not bound to any component
    }

    fun start() {
        if (mediaPlayer.isPlaying.not())
            mediaPlayer.start()
    }

    fun pause() {
        mediaPlayer.pause()
    }

    fun resume() {
        mediaPlayer.pause()
    }

    fun stop() {
        mediaPlayer.stop()
        mediaPlayer.release()
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("AhmedZain", "stop()")
        stop()
    }
}