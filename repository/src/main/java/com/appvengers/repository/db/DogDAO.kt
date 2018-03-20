package com.appvengers.repository.db

import com.appvengers.repository.mappers.map
import com.appvengers.repository.models.DogEntityWrapper
import com.appvengers.db.DaoSession

//TODO("Gestion de errores")
class DogDAO(session: DaoSession): DAOPersistable<DogEntityWrapper>
{
    private val dogEntityDao = session.dogEntityDao

    override fun insert(element: DogEntityWrapper): String
    {
        dogEntityDao.insert(element.map(element.userId))
        return element._id
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

    override fun delete(databaseID: String): Boolean
    {
        dogEntityDao.deleteByKey(databaseID)
        return true
    }

    override fun deleteAll(): Boolean
    {
        dogEntityDao.deleteAll()
        return true
    }

    override fun query(databaseID: String): DogEntityWrapper?
    {
        return dogEntityDao.load(databaseID).map()
    }

    override fun queryAll(): List<DogEntityWrapper>
    {
        return dogEntityDao.loadAll().map { it.map() }
    }
}