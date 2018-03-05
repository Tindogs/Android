package com.appvengers.business.interactors.userCRUD

import com.appvengers.business.models.User

interface GetUserInteractor
{
    fun execute(username: String, password: String): User
    fun execute(userId: Long): User
}