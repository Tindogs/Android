package com.appvengers.business.interactors

import com.appvengers.business.models.Dog

interface NewDogLikeInteractor {
    fun execute(userId: String, dogLiked: Dog, dogLocalId: String, likeValue: Boolean, token: String, success: (isMatch: Boolean, dogMatched: Dog?) -> Unit, error: (msg: String) -> Unit)
}