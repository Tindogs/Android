package com.appvengers.business.interactors.userCRUD

import com.appvengers.business.models.User

interface GetUserInteractor
{
    fun execute(email: String, password: String, success: (user: User) -> Unit, error: (message: String) -> Unit)
    fun execute(userId: String, success: (user: User) -> Unit, error: (message: String) -> Unit)
}