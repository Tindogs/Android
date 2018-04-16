package com.appvengers.tindogs.match

import com.appvengers.business.models.Dog

/**
 * Created by carlosledesma on 9/4/18.
 */
interface MatchContract {
    interface View {
        fun updateDogsList(dogs: MutableList<Dog>)
        fun onMatchViewError(msg: String)

    }

    interface Presenter {
        fun getDogsList(userId: String, dogId: String, token: String)
        fun newDogLike(dog: Dog)
        fun newDogDislike(dog: Dog)


    }

}