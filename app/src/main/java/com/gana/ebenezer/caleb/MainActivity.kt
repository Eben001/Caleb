package com.gana.ebenezer.caleb

import android.Manifest
import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import com.gana.ebenezer.caleb.anim.WavesAnimation
import com.gana.ebenezer.caleb.playback.AndroidAudioPlayer
import com.gana.ebenezer.caleb.record.AndroidAudioRecorder
import com.gana.ebenezer.caleb.ui.theme.CalebTheme
import com.gana.ebenezer.caleb.ui.theme.md_theme_light_primary
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.io.File
import java.io.FileOutputStream


class MainActivity : ComponentActivity() {
    private val recorder by lazy {
        AndroidAudioRecorder(applicationContext)
    }

    private val player by lazy {
        AndroidAudioPlayer()
    }

    private var audioFile: File? = null

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ActivityCompat.requestPermissions(
            this,
            arrayOf(Manifest.permission.RECORD_AUDIO),
            0
        )

        setContent {
            CalebTheme {
                val audioData by viewModel.audioData.collectAsState()
                Scaffold(topBar = { CalebTopBar() }) { paddingValue ->
                    paddingValue.calculateTopPadding()

                    Surface(
                        modifier = Modifier.fillMaxSize(),
                        color = md_theme_light_primary
                    ) {
                        WavesAnimation(onStartRecord = {
                            File(cacheDir, "audio.mp3").also {
                                recorder.start(it)
                                audioFile = it
                            }

                        }, onStopRecord = {
                            recorder.stop()
                            audioFile?.let {
                                sendAudioFile(it)
                            }
                        })
                    }
                }
                audioData?.let {
                    player.playFile(audioDataToFile(applicationContext,it))
                }

            }
        }
    }

    private fun audioDataToFile(context: Context, audioData: ByteArray): File {
        val file = File(context.cacheDir, "temp_audio.mp3")
        FileOutputStream(file).use { fos ->
            fos.write(audioData)
        }
        return file
    }


    private fun sendAudioFile(audioFile: File?) {
        viewModel.sendAudioFile(audioFile)
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CalebTheme {

    }
}