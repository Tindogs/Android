package com.appvengers.business.interactors

import com.appvengers.business.models.Dog
import com.appvengers.business.models.User

interface GetUserDogsInteractor
{
    fun execute(user: User): List<Dog>
}