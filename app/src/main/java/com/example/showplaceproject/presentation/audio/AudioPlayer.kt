package com.example.showplaceproject.presentation.audio

import android.content.Context
import android.net.Uri
import android.os.Handler
import android.os.Looper
import androidx.lifecycle.MutableLiveData
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.datasource.HttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory

@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
class AudioPlayer(context: Context, url: String? = null) {
    private var mediaPlayer: ExoPlayer? = null
    private var isPlaying = false
    private var progress: Long = 0
    private var onProgressUpdate: ((Long, Long) -> Unit) = { _, _ ->

    }

    var duration: MutableLiveData<Long> = MutableLiveData(0)

    init {
        if (mediaPlayer == null) {
            val httpDataSourceFactory: HttpDataSource.Factory =
            DefaultHttpDataSource.Factory().setAllowCrossProtocolRedirects(false)
            val dataSourceFactory: DataSource.Factory = DataSource.Factory {
                val dataSource = httpDataSourceFactory.createDataSource()
                dataSource.setRequestProperty(
                    "cookie", "cookieValue"
                )
                dataSource.setRequestProperty("Range", "1-10000")
                dataSource
            }

            mediaPlayer = ExoPlayer.Builder(context)
                .setMediaSourceFactory(DefaultMediaSourceFactory(dataSourceFactory)).build()
        }

        try {
            val mediaItem = MediaItem.Builder()
                .setUri(Uri.parse(url))
                .build()
            mediaPlayer?.setMediaItem(mediaItem)
            mediaPlayer?.playWhenReady = false
            mediaPlayer?.prepare()
            mediaPlayer?.addListener (object: Player.Listener {
                override fun onPlaybackStateChanged(playbackState: Int) {
                    super.onPlaybackStateChanged(playbackState)
                    if (playbackState === Player.STATE_READY) {
                        duration.value = mediaPlayer?.duration ?: 0
                    }
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun playAudio(url: String, onProgressUpdate: (Long, Long) -> Unit) {
        this.onProgressUpdate = onProgressUpdate

        try {
            mediaPlayer?.play()
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
            mediaPlayer?.play()
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