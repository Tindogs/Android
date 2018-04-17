package com.appvengers.repository.db

import com.appvengers.repository.mappers.map
import com.appvengers.repository.models.DogEntityWrapper
import com.appvengers.db.DaoSession
import com.appvengers.repository.mappers.mapToPhotoEntity

//TODO("Gestion de errores")
class DogDAO(session: DaoSession): DAOPersistable<DogEntityWrapper>
{

    private val dogEntityDao = session.dogEntityDao
    private val dogLikeEntityDao by lazy { session.dogLikeEntityDao }
    private val photosEntityDao by lazy { session.photosEntityDao }
    private val queryEntityDao by lazy { session.queryEntityDao }

    override fun insert(element: DogEntityWrapper): String
    {
        dogEntityDao.insert(element.map())
        return element._id
    }

    override fun insert(list: List<DogEntityWrapper>): Boolean
    {
        list.forEach { dog: DogEntityWrapper ->

        dogEntityDao.insertOrReplace(dog.map())

        dog.likesFromOthers.forEach{
            dogLikeEntityDao.insertOrReplace(it.map(dog._id))
        }

        dog.photos.forEach {
            photosEntityDao.insertOrReplace(it.mapToPhotoEntity(dog._id))
        }
        queryEntityDao.insertOrReplace(dog.query.map(dog._id))

    }
        return true
    }

    override fun update(element: DogEntityWrapper): Boolean
    {
        dogEntityDao.update(element.map())
        return true
    }

    override fun delete(element: DogEntityWrapper): Boolean
    {
        dogEntityDao.delete(element.map())
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

    override fun queryAllWithId(id: String): List<DogEntityWrapper> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}