package com.gana.ebenezer.caleb.playback

import java.io.File

interface AudioPlayer {
    fun playFile(file:File)
    fun stop()

}