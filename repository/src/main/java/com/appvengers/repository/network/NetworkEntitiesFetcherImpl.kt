package com.appvengers.repository.network

import com.appvengers.repository.models.UserEntity

internal class NetworkEntitiesFetcherImpl(private val networkManager: NetworkManager): NetworkEntitiesFetcher
{
    override fun getUser(username: String, password: String, success: (user: UserEntity) -> Unit, error: (message: String) -> Unit)
    {
        TODO("Delvolver un usuario")
    }
}