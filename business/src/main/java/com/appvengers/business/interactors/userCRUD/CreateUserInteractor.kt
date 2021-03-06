package com.appvengers.business.interactors.userCRUD

import com.appvengers.business.models.User

interface CreateUserInteractor
{
    fun execute(firstName: String,
                lastName: String,
                  email: String,
                userName: String,
                password: String,
                photo: String,
                success: (user: User, token: String) -> Unit,
                error: (message: String) -> Unit)
}