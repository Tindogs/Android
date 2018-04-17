package com.appvengers.repository.db

import com.appvengers.repository.models.DogLikeEntityWrapper

/**
 * Created by carlosledesma on 17/4/18.
 */
class DogLikesDAO: DAOPersistable<DogLikeEntityWrapper> {
    override fun insert(element: DogLikeEntityWrapper): String {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun insert(list: List<DogLikeEntityWrapper>): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun update(element: DogLikeEntityWrapper): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(element: DogLikeEntityWrapper): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun delete(databaseID: String): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun deleteAll(): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun query(databaseID: String): DogLikeEntityWrapper? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun queryAll(): List<DogLikeEntityWrapper> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}