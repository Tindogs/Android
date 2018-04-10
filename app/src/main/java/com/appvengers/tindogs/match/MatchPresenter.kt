package com.appvengers.tindogs.match

import com.appvengers.business.interactors.GetDogsListInteractor
import com.appvengers.tindogs.userProfile.UserProfileContract

/**
 * Created by carlosledesma on 10/4/18.
 */
class MatchPresenter(private  val view: MatchContract.View,
                     private val getDogsListInteractor: GetDogsListInteractor): MatchContract.Presenter {
    override fun getDogsList() {
        getDogsListInteractor.execute("",success = {
            view.updateDogsList(it)
        },error = {

        })
    }


}