package com.appvengers.business.interactors.dogCRUD

import com.appvengers.business.models.Dog
import com.appvengers.business.models.User

interface UpdateDogInteractor
{
    fun execute(user: User, dog: Dog): Dog
}