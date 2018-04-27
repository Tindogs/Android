package com.appvengers.tindogs.dogProfile

import com.appvengers.business.interactors.GetDogDetailInteractor

class DogProfilePresenter(private val view: DogProfileContract.View, private val getDogDetailInteractor: GetDogDetailInteractor  ): DogProfileContract.Presenter {

    override fun getDogProfile(userId: String, dogId: String, token: String) {
        getDogDetailInteractor.execute(userId,dogId,token,success = {

        }, error = {

        })
    }
}