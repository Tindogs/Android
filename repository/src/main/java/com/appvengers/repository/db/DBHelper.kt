package com.appvengers.repository.db


import android.content.Context
import com.appvengers.repository.models.DaoMaster
import com.appvengers.repository.models.DaoSession
import com.appvengers.repository.models.DogEntityDao
import com.appvengers.repository.models.UserEntityDao


class DBHelper(private val context: Context)
{

    private val helper = DaoMaster.DevOpenHelper(context, "tindogs-db", null)
    private val db = helper.getWritableDatabase()!!
    private val daoMaster = DaoMaster(db)
    private val daoSession = daoMaster.newSession()

    fun getSession(): DaoSession
    {
        return daoSession
    }
}
