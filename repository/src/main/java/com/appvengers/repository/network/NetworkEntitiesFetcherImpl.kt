package com.appvengers.repository.network

import android.util.Log
import com.appvengers.repository.network.model.DogJsonEntity
import com.appvengers.repository.network.model.DogLikeJsonEntity
import com.appvengers.repository.network.model.QueryJsonEntity
import com.appvengers.repository.network.model.UserJsonEntity
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.*

internal class NetworkEntitiesFetcherImpl(private val networkManager: NetworkManager): NetworkEntitiesFetcher
{
    override fun getUser(userId: String): Flowable<UserJsonEntity>
    {
        return networkManager.getUserById(userId, "letmein")
            .subscribeOn(Schedulers.io())

    }

    override fun getUser(email: String, password: String): Flowable<UserJsonEntity>
    {
       return networkManager.getUser("Sergio80@hotmail.com", "letmein")
                .subscribeOn(Schedulers.io())

    }
}