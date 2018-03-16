package com.appvengers.repository.db


import android.content.Context
import com.appvengers.db.DaoMaster
import com.appvengers.db.DaoSession


class DBHelper(context: Context)
{

    private val helper = DaoMaster.DevOpenHelper(context, "tindogs-db", null)
    private val db = helper.writableDatabase!!
    private val daoMaster = DaoMaster(db)
    private val daoSession = daoMaster.newSession()

    fun getSession(): DaoSession
    {
        return daoSession
    }
}
