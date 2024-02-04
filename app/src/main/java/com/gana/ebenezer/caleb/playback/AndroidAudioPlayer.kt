package com.gana.ebenezer.caleb.playback

import android.content.Context
import android.media.AudioAttributes
import android.media.MediaPlayer
import java.io.File


class AndroidAudioPlayer : AudioPlayer {

    private var player: MediaPlayer? = null

    override fun playFile(file: File) {
        val mediaPlayer = MediaPlayer().apply {
            setAudioAttributes(
                AudioAttributes.Builder()
                    .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                    .setUsage(AudioAttributes.USAGE_MEDIA)
                    .build()
            )
            setDataSource(file.path)
            prepareAsync()
            setOnPreparedListener { mp -> mp.start() }
        }
        player = mediaPlayer
    }


    override fun stop() {
        player?.stop()
        player?.release()
        player = null
    }
}