package com.gana.ebenezer.caleb.record

import java.io.File

interface AudioRecorder {
    fun start(outputFile: File)
    fun stop()
}