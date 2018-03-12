package com.appvengers.repository

import com.appvengers.repository.cache.Cache
import com.appvengers.repository.mappers.map
import com.appvengers.repository.models.UserEntity
import com.appvengers.repository.models.UserEntityWrapper
import com.appvengers.repository.network.NetworkEntitiesFetcher
import com.appvengers.repository.network.model.UserJsonEntity

internal class RepositoryImpl(private val cache: Cache, private val networkEntitiesFetcher: NetworkEntitiesFetcher): Repository
{
    override fun getUser(username: String, password: String, success: (user: UserEntityWrapper) -> Unit, error: (message: String) -> Unit)
    {
        networkEntitiesFetcher.getUser(username, password,
                success = {userJsonEntity: UserJsonEntity ->
                    cache.saveUser(userJsonEntity.map(),
                            success = {

                            },
                            error = {
                                error(it)
                            })
                    success(userJsonEntity.map())
                },
                error = {
                    error(it)
                })
    }


    override fun getUser(userId: Long, success: (user: UserEntityWrapper) -> Unit, error: (message: String) -> Unit)
    {
        cache.getUser(userId,
                success = {userEntity: UserEntityWrapper ->
                    success(userEntity)
                },
                error = {
                    populateCache(success, error)
                })
    }

    private fun populateCache(success: (user: UserEntityWrapper) -> Unit, error: (message: String) -> Unit)
    {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}