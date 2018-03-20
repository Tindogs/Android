package com.appvengers.business.interactors.userCRUD

import com.appvengers.business.models.User

interface UpdateUserInteractor
{
    fun execute(user: User, token: String,  success: (user: User) -> Unit, error: (message: String) -> Unit)
}