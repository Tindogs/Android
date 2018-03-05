package com.appvengers.business.interactors.userCRUD

import com.appvengers.business.models.User

interface DeleteUserInteractor
{
    fun execute(user: User): Boolean
}