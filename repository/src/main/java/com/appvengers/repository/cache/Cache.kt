package com.appvengers.repository.cache

import com.appvengers.repository.models.UserEntity

interface Cache
{
    fun getUser(userId: Long, success: (user: UserEntity) -> Unit, error: (message: String) -> Unit)
}