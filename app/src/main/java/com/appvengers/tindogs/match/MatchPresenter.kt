package com.appvengers.tindogs.match

import com.appvengers.business.interactors.GetDogsListInteractor
import com.appvengers.business.interactors.NewDogDislikeInteractor
import com.appvengers.business.interactors.NewDogLikeInteractor
import com.appvengers.business.models.Dog
import com.appvengers.tindogs.userProfile.UserProfileContract

class MatchPresenter(private  val view: MatchContract.View,
                     private val getDogsListInteractor: GetDogsListInteractor,
                     private val newDogLikeInteractor: NewDogLikeInteractor,
                     private val newDogDislikeInteractor: NewDogDislikeInteractor): MatchContract.Presenter {

    override fun newDogLike(dog: Dog) {
        newDogLikeInteractor.execute(dog._id, success = {

        }, error = {
            view.onMatchViewError(it)
        })
    }

    override fun newDogDislike(dog: Dog) {
        newDogDislikeInteractor.execute(dog._id,success = {

        }, error = {
            view.onMatchViewError(it)
        })
    }

    override fun getDogsList(userId: String, dogId: String, token: String) {

        getDogsListInteractor.execute(userId,dogId,token,success = {
            view.updateDogsList(it)
        },error = {
            view.onMatchViewError(it)
        })
    }


}