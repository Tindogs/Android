package com.appvengers.business.interactors.dogCRUD

import com.appvengers.business.models.Dog
import com.appvengers.business.models.User

interface CreateDogInteractor
{
    fun execute(user: User, dog: Dog): Dog
}