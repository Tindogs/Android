package com.appvengers.business.interactors

import com.appvengers.business.mappers.map
import com.appvengers.business.models.Dog
import com.appvengers.repository.Repository

class NewDogDislikeInteractorImpl(private val repository: Repository) : NewDogDislikeInteractor {
    override fun execute(userId: String, dogLiked: Dog, dogLocalId: String, likeValue: Boolean, token: String, success: () -> Unit, error: (msg: String) -> Unit) {
        repository.newDogLike(userId,dogLiked.map(""),dogLocalId,likeValue,token, success = {

            success()
        }, error = {
            error(it)
        })
    }
}