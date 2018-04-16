package com.appvengers.business.interactors

/**
 * Created by carlosledesma on 15/4/18.
 */
interface NewDogLikeInteractor {
    fun execute(dogId: String, success: () -> Unit, error: (msg: String) -> Unit)
}