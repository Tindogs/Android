package com.appvengers.repository.db

import com.appvengers.db.DaoSession
import com.appvengers.db.DogLikeEntityDao
import com.appvengers.repository.mappers.map
import com.appvengers.repository.models.DogLikeEntityWrapper

/**
 * Created by carlosledesma on 17/4/18.
 */
class DogLikesDAO(private val session: DaoSession): DAOPersistable<DogLikeEntityWrapper> {


    private val dogLikeEntityDao by lazy { session.dogLikeEntityDao }

    override fun insert(element: DogLikeEntityWrapper): String {
        TODO("Innecesario") //To change body of created functions use File | Settings | File Templates.
    }

    override fun insert(list: List<DogLikeEntityWrapper>): Boolean {
        TODO("Innecesario") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(element: DogLikeEntityWrapper): Boolean {
        TODO("Innecesario") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(element: DogLikeEntityWrapper): Boolean {
        TODO("Innecesario") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(databaseID: String): Boolean {
        TODO("Innecesario") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteAll(): Boolean {
        TODO("Innecesario") //To change body of created functions use File | Settings | File Templates.
    }

    override fun query(databaseID: String): DogLikeEntityWrapper? {
        TODO("Inncesario")
    }

    override fun queryAll(): List<DogLikeEntityWrapper> {
        TODO("Innecesario")
    }

    override fun queryAllWithId(id: String): List<DogLikeEntityWrapper> {
        var returnList : MutableList<DogLikeEntityWrapper> = mutableListOf()
        var list = dogLikeEntityDao.queryBuilder().where(DogLikeEntityDao.Properties.DogIdLiked.eq(id)).build().list()
        list.forEach {
            var item =  it.map()
            returnList.add(item)
        }
        return returnList
    }
}