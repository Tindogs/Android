package com.appvengers.repository

import com.appvengers.repository.models.UserEntityWrapper

interface Repository
{
    fun getUser(email: String, password: String, success: (user: UserEntityWrapper) -> Unit, error: (message: String) -> Unit)
    fun getUser(userId: String, success: (user: UserEntityWrapper) -> Unit, error: (message: String) -> Unit)
}