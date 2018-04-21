package com.appvengers.business.interactors

import com.appvengers.business.models.Dog

interface NewDogDislikeInteractor {

    fun execute(userId: String, dogLiked: Dog, dogLocalId: String, likeValue: Boolean, token: String, success: () -> Unit, error: (msg: String) -> Unit)
}