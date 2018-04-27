package com.appvengers.business.interactors

import com.appvengers.business.mappers.mapToDog
import com.appvengers.business.models.Dog
import com.appvengers.repository.Repository

class GetDogDetailInteractorImpl(private val repository: Repository) : GetDogDetailInteractor {
    override fun execute(userId: String, dogId: String, token: String, success: (dog: Dog) -> Unit, error: (msg: String) -> Unit) {
        repository.getDogDetail(userId, dogId, token, success = {
            success(it.mapToDog())
        }, error = {

        })
    }
}