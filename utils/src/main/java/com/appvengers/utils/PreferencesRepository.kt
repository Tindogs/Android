package com.appvengers.utils


import android.content.Context


import java.lang.ref.WeakReference

class PreferencesRepository(val context: WeakReference<Context>)
{
    fun getTokenAndUserId(fileName:String, tokenName: String, userIdName: String): Pair<String, String>?
    {
        val preferences = context.get()!!.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        val token = preferences.getString(tokenName,null)
        val userId = preferences.getString(userIdName, null)
        return if (token != null && userId != null)
        {
            Pair(token, userId)
        }  else
        {
            null
        }
    }

    fun saveTokenAndUserId(fileName:String, tokenName: String, token: String, userIdName: String, userId: String)
    {
        val preferences = context.get()!!.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.putString(tokenName, token)
        editor.putString(userIdName, userId)
        editor.apply()
    }
}