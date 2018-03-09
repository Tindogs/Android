package com.appvengers.repository.di

import android.content.Context
import com.appvengers.repository.Repository
import com.appvengers.repository.RepositoryImpl
import com.appvengers.repository.cache.Cache
import com.appvengers.repository.cache.CacheImpl
import com.appvengers.repository.db.DAOPersistable
import com.appvengers.repository.db.DBHelper
import com.appvengers.repository.db.UserDAO
import com.appvengers.repository.models.UserEntity
import com.appvengers.repository.network.NetworkEntitiesFetcher
import java.lang.ref.WeakReference

class RepositoryObjectInjector(private val weakContext: WeakReference<Context>)
{
    fun buildRepository(): Repository
    {
        return RepositoryImpl(buildCache(), buildNetworkEntitiesFetcher())
    }

    private fun buildCache(): Cache
    {
        return CacheImpl(buildUserDaoPersistable())
    }

    private fun buildNetworkEntitiesFetcher(): NetworkEntitiesFetcher
    {
        TODO()
    }

    private fun buildUserDaoPersistable(): DAOPersistable<UserEntity>
    {
        return UserDAO(DBHelper(weakContext.get()!!).getUserDao())
    }
}