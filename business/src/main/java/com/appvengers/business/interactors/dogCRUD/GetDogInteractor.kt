package com.appvengers.business.interactors.dogCRUD

import com.appvengers.business.models.Dog
import com.appvengers.business.models.User

interface GetDogInteractor
{
    fun execute(dogId: Long): Dog
    fun execute(user: User, position: Int): Dog
}