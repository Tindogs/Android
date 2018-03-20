package com.appvengers.utils

import android.util.Log

const val TINDOGS = "Tindogs"
fun LogTindogs(message: String, level: Int)
{
    when (level)
    {
        Log.ERROR -> Log.e(TINDOGS, message)
        Log.DEBUG -> Log.d(TINDOGS, message)
        Log.WARN -> Log.w(TINDOGS, message)
        Log.INFO -> Log.i(TINDOGS, message)
        Log.VERBOSE -> Log.v(TINDOGS, message)
    }


}