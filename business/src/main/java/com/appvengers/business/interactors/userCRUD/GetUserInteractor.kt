package com.appvengers.business.interactors.userCRUD

import com.appvengers.business.models.User

interface GetUserInteractor
{
    fun execute(username: String, password: String, success: (user: User) -> Unit, error: (message: String) -> Unit)
    fun execute(userId: Long, success: (user: User) -> Unit, error: (message: String) -> Unit)
}