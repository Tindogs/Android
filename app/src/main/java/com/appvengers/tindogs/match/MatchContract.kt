package com.appvengers.tindogs.match

import com.appvengers.business.models.Dog

interface MatchContract {
    interface View {
        fun updateDogsList(dogs: MutableList<Dog>)
        fun onMatchViewError(msg: String)

    }

    interface Presenter {
        fun getDogsList(userId: String, dogId: String, token: String)
        fun newDogLike(userId: String, dog: Dog,localDogId: String,token: String)
        fun newDogDislike(userId: String, dog: Dog, localDogId: String, token: String)


    }

}