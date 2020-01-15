package com.zainco.library.archcomponents.lifecycle.casterio.deepdive

import android.content.Context
import android.media.MediaPlayer
import com.zainco.library.R

class MusicManager(context: Context) {

    private var mediaPlayer: MediaPlayer = MediaPlayer.create(context, R.raw.asalat)

    fun start() {
        if (mediaPlayer.isPlaying.not())
            mediaPlayer.start()
    }

    fun pause() {
        mediaPlayer.pause()
    }

    fun stop() {
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}