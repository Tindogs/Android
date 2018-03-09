package com.appvengers.repository.db


import android.content.Context
import com.appvengers.repository.models.DaoMaster
import com.appvengers.repository.models.UserEntityDao


class DBHelper(private val context: Context)
{
    fun getUserDao(): UserEntityDao
    {
        val helper = DaoMaster.DevOpenHelper(context, "tindogs-db", null)
        val db = helper.getWritableDatabase()
        val daoMaster = DaoMaster(db)
        val daoSession = daoMaster.newSession()
        return daoSession.userEntityDao
    }
}
