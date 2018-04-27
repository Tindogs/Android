package com.appvengers.tindogs.match

import android.util.Log
import com.appvengers.business.interactors.GetDogsListInteractor
import com.appvengers.business.interactors.NewDogDislikeInteractor
import com.appvengers.business.interactors.NewDogLikeInteractor
import com.appvengers.business.models.Dog
import com.appvengers.utils.LogTindogs

class MatchPresenter(private  val view: MatchContract.View,
                     private val getDogsListInteractor: GetDogsListInteractor,
                     private val newDogLikeInteractor: NewDogLikeInteractor,
                     private val newDogDislikeInteractor: NewDogDislikeInteractor): MatchContract.Presenter {

    override fun newDogLike(userId: String, dog: Dog, localDogId: String, token: String) {
            LogTindogs(dog.name, Log.DEBUG)
            newDogLikeInteractor.execute(userId,dog,localDogId,true,token, success =  { b: Boolean, dog: Dog? ->
                if(b) {
                    view.onMatchView("Es un match!")
                }
            },error = {
                view.onMatchViewError(it)
            })
    }

    override fun newDogDislike(userId: String, dog: Dog, localDogId: String, token: String) {

        newDogDislikeInteractor.execute(userId,dog, localDogId, false, token, success = {

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