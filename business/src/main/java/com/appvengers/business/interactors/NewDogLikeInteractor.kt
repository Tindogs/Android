package com.appvengers.business.interactors

interface NewDogLikeInteractor {
    fun execute(dogId: String, success: () -> Unit, error: (msg: String) -> Unit)
}