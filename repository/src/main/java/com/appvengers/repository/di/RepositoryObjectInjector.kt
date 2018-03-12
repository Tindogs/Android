package com.appvengers.repository.di

import android.content.Context
import com.appvengers.repository.Repository
import com.appvengers.repository.RepositoryImpl
import com.appvengers.repository.cache.Cache
import com.appvengers.repository.cache.CacheImpl
import com.appvengers.repository.db.DAOPersistable
import com.appvengers.repository.db.DBHelper
import com.appvengers.repository.db.DogDAO
import com.appvengers.repository.db.UserDAO
import com.appvengers.repository.models.*
import com.appvengers.repository.network.NetworkEntitiesFetcher
import java.lang.ref.WeakReference

class RepositoryObjectInjector(private val weakContext: WeakReference<Context>)
{
    val dbHelper: DBHelper by lazy { DBHelper(weakContext.get()!!) }
    fun buildRepository(): Repository
    {
        return RepositoryImpl(buildCache(), buildNetworkEntitiesFetcher())
    }

    internal fun buildCache(): Cache
    {
        return CacheImpl(buildUserDaoPersistable())
    }

    internal fun buildNetworkEntitiesFetcher(): NetworkEntitiesFetcher
    {
        TODO()
    }

    internal fun buildUserDaoPersistable(): DAOPersistable<UserEntityWrapper>
    {
        return UserDAO(dbHelper.getSession())
    }

    internal fun buildDogDaoPersistable(): DAOPersistable<DogEntityWrapper>
    {
        return DogDAO(dbHelper.getSession())
    }


}