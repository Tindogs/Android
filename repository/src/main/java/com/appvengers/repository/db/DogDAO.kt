package com.appvengers.repository.db

import com.appvengers.repository.mappers.map
import com.appvengers.repository.models.DaoSession
import com.appvengers.repository.models.DogEntity
import com.appvengers.repository.models.DogEntityWrapper

class DogDAO(private val session: DaoSession): DAOPersistable<DogEntityWrapper>
{
    private val dogEntityDao = session.dogEntityDao

    override fun insert(element: DogEntityWrapper): Long
    {
        return dogEntityDao.insert(element.map(element.userId))
    }



    override fun update(element: DogEntityWrapper): Boolean
    {
        dogEntityDao.update(element.map(element.userId))
        return true
    }

    override fun delete(element: DogEntityWrapper): Boolean
    {
        dogEntityDao.delete(element.map(element.userId))
        return true
    }

    override fun delete(databaseID: Long): Boolean
    {
        dogEntityDao.deleteByKey(databaseID)
        return true
    }

    override fun deleteAll(): Boolean
    {
        dogEntityDao.deleteAll()
        return true
    }

    override fun query(databaseID: Long): DogEntityWrapper?
    {
        return dogEntityDao.load(databaseID).map()
    }

    override fun queryAll(): List<DogEntityWrapper>
    {
        return dogEntityDao.loadAll().map { it.map() }
    }
}