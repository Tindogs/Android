package com.appvengers.repository

import com.appvengers.repository.models.UserEntityWrapper

interface Repository
{
    fun getUser(username: String, password: String, success: (user: UserEntityWrapper) -> Unit, error: (message: String) -> Unit)
    fun getUser(userId: Long, success: (user: UserEntityWrapper) -> Unit, error: (message: String) -> Unit)
}