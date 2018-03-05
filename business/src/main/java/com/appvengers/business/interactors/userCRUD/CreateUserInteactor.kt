package com.appvengers.business.interactors.userCRUD

import com.appvengers.business.models.User

interface CreateUserInteactor
{
    fun execute(user: User): User
}