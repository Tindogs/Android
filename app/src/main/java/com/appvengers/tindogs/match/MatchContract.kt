package com.appvengers.tindogs.match

import com.appvengers.business.models.Dog

/**
 * Created by carlosledesma on 9/4/18.
 */
interface MatchContract {
    interface View {
        fun updateDogsList(dogs: MutableList<Dog>)

    }

    interface Presenter {
        fun getDogsList()

    }

}