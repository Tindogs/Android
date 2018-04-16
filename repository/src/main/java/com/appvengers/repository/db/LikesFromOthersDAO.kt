package com.appvengers.repository.db

import com.appvengers.db.DaoSession

import com.appvengers.repository.models.LikesFromOthersEntityWrapper

class LikesFromOthersDAO(session: DaoSession): DAOPersistable<LikesFromOthersEntityWrapper> {



    override fun insert(element: LikesFromOthersEntityWrapper): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun insert(list: List<LikesFromOthersEntityWrapper>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(element: LikesFromOthersEntityWrapper): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(element: LikesFromOthersEntityWrapper): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(databaseID: String): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteAll(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun query(databaseID: String): LikesFromOthersEntityWrapper? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun queryAll(): List<LikesFromOthersEntityWrapper> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
