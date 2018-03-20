package com.appvengers.repository

import com.appvengers.repository.models.UserEntityWrapper

interface Repository
{
    fun getUser(email: String, password: String, success: (user: UserEntityWrapper, token: String) -> Unit, error: (message: String) -> Unit)
    fun getUser(userId: String, token: String,  success: (user: UserEntityWrapper) -> Unit, error: (message: String) -> Unit)
    fun createUser(
            firstName: String,
            lastName: String,
            phone: String,
            mobilePhone: String,
            email: String,
            userName: String,
            password: String,
            success: (user: UserEntityWrapper, token: String) -> Unit,
            error: (message: String) -> Unit
    )
    fun updateUser(user: UserEntityWrapper, token: String, success: (user: UserEntityWrapper) -> Unit, error: (message: String) -> Unit)
}