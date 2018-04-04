package com.appvengers.utils


import android.content.Context


import java.lang.ref.WeakReference

class PreferencesRepository(val context: WeakReference<Context>)
{
    fun getTokenAndUserId(fileName:String, tokenName: String, userIdName: String): KeyUserInfo?
    {
        val preferences = context.get()!!.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        val token = preferences.getString(tokenName,null)
        val userId = preferences.getString(userIdName, null)
        return if (token != null && userId != null)
        {
            KeyUserInfo(token, userId)
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

    fun deleteUserInfo(fileName:String, tokenName: String,  userIdName: String)
    {
        val preferences = context.get()!!.getSharedPreferences(fileName, Context.MODE_PRIVATE)
        val editor = preferences.edit()
        editor.remove(tokenName)
        editor.remove(userIdName)
        editor.apply()
    }
}
data class KeyUserInfo(val token: String, val userId: String)