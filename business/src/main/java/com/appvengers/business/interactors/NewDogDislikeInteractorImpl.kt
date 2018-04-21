package com.appvengers.business.interactors

import com.appvengers.business.models.Dog

class NewDogDislikeInteractorImpl : NewDogDislikeInteractor {
    override fun execute(dogId: String, dogLiked: Dog, dogLocalId: String, likeValue: Boolean, token: String, success: () -> Unit, error: (msg: String) -> Unit) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}