package com.appvengers.business.interactors.dogCRUD

import com.appvengers.business.models.Dog

interface DeleteDogInteractor
{
    fun execute(dog: Dog): Boolean
}