package com.gana.ebenezer.caleb

import android.util.Log
import com.gana.ebenezer.caleb.ApiService
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import retrofit2.HttpException
import java.io.File
import java.io.IOException

class Repository(private val apiService: ApiService) {

    suspend fun sendAudioFile(audioFile: File?):ByteArray?{
        if (audioFile == null) {
            // Handle the case where the audioFile is null
            Log.e("Error", "Audio file is null")
            return null

        }

        val requestFile = audioFile.asRequestBody("audio/mpeg".toMediaTypeOrNull())
        val audioPart = MultipartBody.Part.createFormData("file", audioFile.name, requestFile)

        try {
            val response = apiService.uploadAudio(audioPart)

            if (response.isSuccessful) {
                val audioResponseBody = response.body()
                if (audioResponseBody != null) {
                    val audioData = audioResponseBody.bytes()
                    Log.d("AudioData", audioData.toString())
                    return audioData

                } else {
                    // Handle the case where the response body is null
                    Log.e("Error", "Response body is null")
                }
            } else {
                // Handle the case where the response is not successful
                Log.e("Error", "Request not successful, HTTP status code: ${response.code()}")
            }
        } catch (e: IOException) {
            // Handle file-related issues
            e.printStackTrace()
            Log.e("Error", "File-related error: ${e.message}")
        } catch (e: HttpException) {
            // Handle HTTP-related issues (e.g., non-200 HTTP status codes)
            e.printStackTrace()
            Log.e("Error", "HTTP error: ${e.code()}")
        } catch (e: Exception) {
            // Handle other unexpected exceptions
            e.printStackTrace()
            Log.e("Error", "Unexpected error: ${e.message}")
        }

        return null

    }
/*
    suspend fun sendAudioFile(audioFile: File?):String?{
        if (audioFile == null) {
            // Handle the case where the audioFile is null
            Log.e("Error", "Audio file is null")
            return null

        }

        val requestFile = audioFile.asRequestBody("audio/mpeg".toMediaTypeOrNull())
        val audioPart = MultipartBody.Part.createFormData("file", audioFile.name, requestFile)

        try {
            val response = apiService.uploadAudio(audioPart)

            if (response.isSuccessful) {
                val audioResponseBody = response.body()
                if (audioResponseBody != null) {
                    val audioData = audioResponseBody.string()
                    Log.d("AudioData", audioData)
                    return audioData

                } else {
                    // Handle the case where the response body is null
                    Log.e("Error", "Response body is null")
                }
            } else {
                // Handle the case where the response is not successful
                Log.e("Error", "Request not successful, HTTP status code: ${response.code()}")
            }
        } catch (e: IOException) {
            // Handle file-related issues
            e.printStackTrace()
            Log.e("Error", "File-related error: ${e.message}")
        } catch (e: HttpException) {
            // Handle HTTP-related issues (e.g., non-200 HTTP status codes)
            e.printStackTrace()
            Log.e("Error", "HTTP error: ${e.code()}")
        } catch (e: Exception) {
            // Handle other unexpected exceptions
            e.printStackTrace()
            Log.e("Error", "Unexpected error: ${e.message}")
        }

        return null

    }
*/


}