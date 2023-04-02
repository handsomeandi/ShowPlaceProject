package com.example.showplaceproject.presentation.audio

import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper

class AudioPlayer(url: String? = null) {
    private var mediaPlayer: MediaPlayer? = null
    private var isPlaying = false
    private var progress = 0
    private var onProgressUpdate: ((Int, Int) -> Unit) = { _, _ ->

    }

    var duration: Int = 0

    init {
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer()
        }

        try {
            mediaPlayer?.setDataSource(url)
            mediaPlayer?.prepare()
            duration = mediaPlayer?.duration ?: 0
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun playAudio(url: String, onProgressUpdate: (Int, Int) -> Unit) {
        this.onProgressUpdate = onProgressUpdate
        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer()
            mediaPlayer?.setDataSource(url)
            mediaPlayer?.prepare()
        }

        try {
            mediaPlayer?.start()
            isPlaying = true

            postProgress()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun pauseAudio() {
        if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
            mediaPlayer?.pause()
            isPlaying = false
        }
    }

    fun resumeAudio() {
        if (mediaPlayer != null && !mediaPlayer!!.isPlaying) {
            mediaPlayer?.start()
            isPlaying = true
            postProgress()
        }
    }

    fun isPlaying(): Boolean {
        return isPlaying
    }

    fun release() {
        if (mediaPlayer != null) {
            mediaPlayer?.release()
            mediaPlayer = null
            progress = 0
            isPlaying = false
        }
    }

    private fun postProgress() {
        val duration = mediaPlayer?.duration ?: 0
        val handler = Handler(Looper.getMainLooper())

        handler.post(object : Runnable {
            override fun run() {
                val mediaPlayerLocal = mediaPlayer
                if (mediaPlayerLocal != null && isPlaying) {
                    progress = mediaPlayerLocal.currentPosition
                    onProgressUpdate(progress, duration)
                    handler.postDelayed(this, 100)
                }
                if(duration - progress < 1000) {
                    mediaPlayer?.stop()
                    release()
                    onProgressUpdate(progress, duration)
                }
            }
        })
    }
}