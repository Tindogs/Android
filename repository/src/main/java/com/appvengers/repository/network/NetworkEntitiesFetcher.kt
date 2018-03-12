package com.appvengers.repository.network

import com.appvengers.repository.network.model.UserJsonEntity

interface NetworkEntitiesFetcher
{
    fun getUser(username: String, password: String, success: (user: UserJsonEntity) -> Unit, error: (message: String) -> Unit)
}