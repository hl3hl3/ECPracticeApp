package com.hl3hl3.ecpracticeapp

import android.util.Log

object Logger {
    fun logD(tag: String, msg : String) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, msg)
        }
    }

    fun logE(exception: Exception) {
        if (BuildConfig.DEBUG) {
            exception.printStackTrace()
        }
    }
}