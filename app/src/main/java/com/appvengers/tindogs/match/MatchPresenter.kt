package com.appvengers.tindogs.match

import com.appvengers.business.interactors.GetDogsListInteractor
import com.appvengers.business.models.Dog
import com.appvengers.tindogs.userProfile.UserProfileContract

/**
 * Created by carlosledesma on 10/4/18.
 */
class MatchPresenter(private  val view: MatchContract.View,
                     private val getDogsListInteractor: GetDogsListInteractor): MatchContract.Presenter {

    override fun newDogLike(dog: Dog) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun newDogDislike(dog: Dog) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getDogsList(userId: String, dogId: String, token: String) {

        getDogsListInteractor.execute(userId,dogId,token,success = {
            view.updateDogsList(it)
        },error = {
            view.onMatchViewError(it)
        })
    }


}