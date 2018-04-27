package com.appvengers.business.interactors

import com.appvengers.business.models.Dog

interface GetDogDetailInteractor {
    fun execute(userId: String, dogId: String, token: String, success: (dog: Dog) -> Unit, error: (msg: String) -> Unit )
}