package com.appvengers.repository.network

import com.appvengers.repository.models.UserEntity

interface NetworkEntitiesFetcher
{
    fun getUser(username: String, password: String, success: (user: UserEntity) -> Unit, error: (message: String) -> Unit)
}