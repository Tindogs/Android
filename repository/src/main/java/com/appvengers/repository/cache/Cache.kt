package com.appvengers.repository.cache

import com.appvengers.repository.models.*

interface Cache
{
    fun getUser(userId: Long, success: (user: UserEntityWrapper) -> Unit, error: (message: String) -> Unit)
    fun saveUser(userEntityWrapper: UserEntityWrapper, success: () -> Unit, error: (message: String) -> Unit)

}