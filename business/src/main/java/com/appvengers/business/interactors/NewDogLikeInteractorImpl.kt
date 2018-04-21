package com.appvengers.business.interactors

import android.util.Log
import com.appvengers.business.mappers.map
import com.appvengers.business.mappers.mapToDog
import com.appvengers.business.models.Dog
import com.appvengers.repository.Repository
import com.appvengers.repository.mappers.map

import com.appvengers.utils.LogTindogs

class NewDogLikeInteractorImpl(private val repository: Repository) : NewDogLikeInteractor {

    override fun execute(userId: String, dogLiked: Dog, dogLocalId: String, likeValue: Boolean, token: String, success: (isMatch: Boolean, dogMatched: Dog?) -> Unit, error: (msg: String) -> Unit) {
        repository.newDogLike(userId,dogLiked.map(""),dogLocalId,likeValue,token,success = {
            LogTindogs(it.toString(), Log.DEBUG)
            if(it.dog != null) {
                success(it.match, it.dog!!.map("").mapToDog())
            } else {
                success(it.match, null)
            }

        },error = {
            error(it)
        })
    }
}