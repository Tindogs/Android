package com.appvengers.repository.db

import com.appvengers.db.DaoSession
import com.appvengers.repository.mappers.map
import com.appvengers.repository.models.QueryEntityWrapper


class QueryDAO(session: DaoSession, dogId: String) : DAOPersistable<QueryEntityWrapper> {

    private val queryEntityDao = session.queryEntityDao
    private val dogId = dogId

    override fun insert(element: QueryEntityWrapper): String {
        queryEntityDao.insert(element.map(dogId))
        return ""
    }

    override fun insert(list: List<QueryEntityWrapper>): Boolean {
        return true
    }

    override fun update(element: QueryEntityWrapper): Boolean {

        return true
    }

    override fun delete(element: QueryEntityWrapper): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(databaseID: String): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteAll(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun query(databaseID: String): QueryEntityWrapper? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun queryAll(): List<QueryEntityWrapper> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}
