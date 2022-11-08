package com.myhand.nationalassembly.util

import android.util.Log

object LogUtill {
    private const val TAG = "LogUtill"

    fun d(msg: String) {
        Log.d(TAG, buildLogMsg(msg))
    }


    fun buildLogMsg(message: String): String {
        val ste = Thread.currentThread().stackTrace[4]
        val sb = StringBuilder()
        sb.append("[")
        sb.append(ste.fileName.replace(".java", ""))
        sb.append("::")
        sb.append(ste.methodName)
        sb.append("]")
        sb.append(message)
        return sb.toString()
    }

}