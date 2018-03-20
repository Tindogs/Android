package com.appvengers.repository.network

import android.util.Log
import com.appvengers.repository.network.model.*
import com.appvengers.utils.LogTindogs
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

internal class NetworkEntitiesFetcherImpl(private val networkManager: NetworkManager): NetworkEntitiesFetcher
{
    override fun createUser(firstName: String, lastName: String, phone: String, mobilePhone: String, email: String, userName: String, password: String): Flowable<ResultUserJson>
    {
        return networkManager.createUser(firstName, lastName, email, userName, password)
    }

    override fun getUserById(userId: String, token: String): Flowable<ResultUserJson>
    {
        LogTindogs("NetworkEntitiesFetcherImpl:  getUserById", Log.DEBUG)
        return networkManager.getUserById(userId, token)
            .subscribeOn(Schedulers.io())

    }

    override fun getUser(email: String, password: String): Flowable<ResultUserJson>
    {
       return networkManager.getUser(email, password)
                .subscribeOn(Schedulers.io())

    }
}