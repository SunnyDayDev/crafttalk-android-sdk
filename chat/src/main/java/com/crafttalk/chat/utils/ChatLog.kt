package com.crafttalk.chat.utils

import android.util.Log

internal object ChatLog : Logger {

    var logger: Logger? = null

    override fun d(tag: String, message: String) {
        logger?.d(tag, message)
    }

    override fun e(tag: String, message: String) {
        logger?.e(tag, message)
    }

}

interface Logger {

    fun d(tag: String, message: String)

    fun e(tag: String, message: String)

}

class DefaultLogger : Logger {

    override fun d(tag: String, message: String) {
        Log.d(tag, message)
    }

    override fun e(tag: String, message: String) {
        Log.e(tag, message)
    }

}