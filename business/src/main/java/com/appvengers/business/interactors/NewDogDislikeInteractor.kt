package com.appvengers.business.interactors

interface NewDogDislikeInteractor {

    fun execute(dogId: String, success: () -> Unit, error: (msg: String) -> Unit)
}