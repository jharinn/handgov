package com.myhand.nationalassembly.util

import android.util.Log
import com.myhand.nationalassembly.BuildConfig

object LogUtil {
    private const val TAG = "LogUtill"

    fun d(msg: String) {
        if (true && !BuildConfig.DEBUG) {
            return
        }

        Log.d(TAG, buildLogMsg(msg))
    }

    fun e(msg: String) {
        if (true && !BuildConfig.DEBUG) {
            return
        }

        Log.e(TAG, buildLogMsg(msg))
    }

    fun buildLogMsg(message: String): String {
        val ste = Thread.currentThread().stackTrace[4]
        val sb = StringBuilder()
        sb.append("[")
        sb.append(ste.fileName.replace(".java", ""))
        sb.append("::")
        sb.append(ste.methodName)
        sb.append("(${ste.fileName}:${ste.lineNumber})")
        sb.append("]")
        sb.append(message)
        return sb.toString()
    }

}