package com.appvengers.tindogs.dogProfile

import com.appvengers.business.models.Dog


interface DogProfileContract {
    interface View {
        fun bindDogData(dog: Dog)
    }

    interface Presenter {
        fun getDogProfile(userId: String, dogId: String, token: String)

    }
}