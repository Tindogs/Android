package com.appvengers.repository

import com.appvengers.repository.cache.Cache
import com.appvengers.repository.models.UserEntity
import com.appvengers.repository.network.NetworkEntitiesFetcher

internal class RepositoryImpl(private val cache: Cache, private val networkEntitiesFetcher: NetworkEntitiesFetcher): Repository
{
    override fun getUser(username: String, password: String, success: (user: UserEntity) -> Unit, error: (message: String) -> Unit)
    {
        networkEntitiesFetcher.getUser(username, password,
                success = {userEntity: UserEntity ->

                    poputateCache(success, error)
                },
                error = {
                    error(it)
                })
    }


    override fun getUser(userId: Long, success: (user: UserEntity) -> Unit, error: (message: String) -> Unit)
    {
        cache.getUser(userId,
                success = {userEntity: UserEntity ->
                    success(userEntity)
                },
                error = {
                    poputateCache(success, error)
                })
    }

    private fun poputateCache(success: (user: UserEntity) -> Unit, error: (message: String) -> Unit)
    {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}