package com.zainco.library.mediaplayer

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.zainco.library.R
import kotlinx.android.synthetic.main.activity_play_pause.*


class PlayPauseActivity : AppCompatActivity() {
    private var mPlayer: MediaPlayer? = null
    private var mHandler: Handler? = null
    private var mRunnable: Runnable? = null
    private val mInterval = 10L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play_pause)
        mHandler = Handler()
        btn_stop.isEnabled = false
        btn_pause.isEnabled = false
        btn_resume.isEnabled = false
        btn_play.setOnClickListener {
            stopPlaying()

            // Initialize media player
            mPlayer = MediaPlayer.create(this, R.raw.adham)

            // Start the media player
            mPlayer?.start()
            Toast.makeText(this, "Media Player is playing.", Toast.LENGTH_SHORT).show()

            // Get the current audio stats
            getAudioStats()
            // Initialize the seek bar
            initializeSeekBar()

            // Disable the start and resume button
            btn_play.isEnabled = false
            btn_resume.isEnabled = false
            btn_pause.isEnabled = true
            btn_stop.isEnabled = true
        }
        btn_stop.setOnClickListener {
            stopPlaying()
            btn_stop.isEnabled = false
            btn_resume.isEnabled = false
            btn_pause.isEnabled = false
            btn_play.isEnabled = true
        }


        // Click listener for pause button

        // Click listener for pause button
        btn_pause.setOnClickListener {
            if (mPlayer != null && mPlayer!!.isPlaying) {
                /*
                    void pause ()
                        Pauses playback. Call start() to resume.

                    Throws
                        IllegalStateException : if the internal player engine has not been initialized.
                */
                mPlayer!!.pause()
                btn_pause.isEnabled = false
                btn_stop.isEnabled = false
                btn_play.isEnabled = false
                btn_resume.isEnabled = true
                btn_play.isEnabled = true
            }
        }

        // Click listener for resume button

        // Click listener for resume button
        btn_resume.setOnClickListener {
            if (mPlayer != null) {
                mPlayer!!.start()
                btn_resume.isEnabled = false
                btn_pause.isEnabled = true
                btn_play.isEnabled = true
                btn_stop.isEnabled = true
            }
        }
        // Set a change listener for seek bar

        // Set a change listener for seek bar
        seek_bar.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                if (mPlayer != null && fromUser) {
                    mPlayer!!.seekTo(progress * mInterval.toInt())
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {}
            override fun onStopTrackingTouch(seekBar: SeekBar) {}
        })
    }


    private fun stopPlaying() {
        if (mPlayer != null) {
            mPlayer?.stop()
            mPlayer?.release()
            mPlayer = null
            Toast.makeText(this, "Stop playing.", Toast.LENGTH_SHORT).show()
            if (mHandler != null) {
                mHandler?.removeCallbacks(mRunnable)
            }
        }

    }

    protected fun getAudioStats() {
        val duration = mPlayer!!.duration / 1000 // In milliseconds
        val due = (mPlayer!!.duration - mPlayer!!.currentPosition) / 1000
        val pass = duration - due
        tv_pass.text = "$pass seconds"
        tv_duration.text = "$duration seconds"
        tv_due.text = "$due seconds"
    }

    protected fun initializeSeekBar() {
        seek_bar.max = mPlayer!!.duration / mInterval.toInt()
        mRunnable = Runnable {
            if (mPlayer != null) {
                val mCurrentPosition: Int =
                    mPlayer!!.currentPosition / mInterval.toInt() // In milliseconds
                seek_bar.progress = mCurrentPosition

                getAudioStats()
            }
            mHandler?.postDelayed(mRunnable, mInterval)
        }
        mHandler?.postDelayed(mRunnable, mInterval)
    }
}
