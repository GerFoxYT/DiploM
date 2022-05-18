package com.example.apptest.utilits

import android.media.MediaRecorder
import java.io.File

class appVoiceRec {

    private val mMediaRecorder = MediaRecorder()
    private lateinit var mFile: File
    private lateinit var mMessageKey: String

    fun startRec(messageKey: String) {
        try {
            mMessageKey = messageKey
            createFileForRec()
            prepareMediaRec()
            mMediaRecorder.start()
        } catch (e: Exception) {
            showToast(e.message.toString())
        }
    }

    private fun prepareMediaRec() {
        mMediaRecorder.apply {
            mMediaRecorder.reset()
            setAudioSource(MediaRecorder.AudioSource.DEFAULT)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT)
            setOutputFile(mFile.absolutePath)
            prepare()
        }


    }

    private fun createFileForRec() {
        mFile = File(APP_ACTIVITY.filesDir, mMessageKey)
        mFile.createNewFile()

    }

    fun stopRec(onSuccess: (file: File, messageKey: String) -> Unit) {
        try {
            mMediaRecorder.stop()
            onSuccess(mFile, mMessageKey)
        } catch (e: java.lang.Exception) {
            showToast(e.message.toString())
            mFile.delete()
        }
    }

    fun releaseRec() {
        try {
            mMediaRecorder.release()
        } catch (e: java.lang.Exception) {
            showToast(e.message.toString())
        }
    }

}
