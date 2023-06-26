package com.jinvita.testretrofit

import android.content.Context
import android.util.Log
import android.widget.Toast

object App {
    var isDebug = true
    var isHttpLog = false
    fun debug(tag: String, msg: String) {
        if (isDebug) Log.d(tag, msg)
    }

    fun error(tag: String, msg: String) {
        if (isDebug) Log.e(tag, msg)
    }

    fun error(tag: String, msg: String, ex: Exception) {
        if (isDebug) Log.e(tag, msg, ex)
    }

    fun showToast(context: Context, msg: String) {
        if (::toast.isInitialized) toast.cancel()
        toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT)
        toast.show()
    }

    private lateinit var toast: Toast
}