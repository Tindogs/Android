package com.appvengers.repository

import com.appvengers.repository.models.UserEntity

interface Repository
{
    fun getUser(username: String, password: String, success: (user: UserEntity) -> Unit, error: (message: String) -> Unit)
    fun getUser(userId: Long, success: (user: UserEntity) -> Unit, error: (message: String) -> Unit)
}