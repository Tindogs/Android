package com.appvengers.business.interactors

import com.appvengers.business.models.Dog

/**
 * Created by carlosledesma on 10/4/18.
 */
interface GetDogsListInteractor {

    fun execute(dogId: String, success: (dogs: MutableList<Dog>) -> Unit, error: (msg: String) -> Unit )
}