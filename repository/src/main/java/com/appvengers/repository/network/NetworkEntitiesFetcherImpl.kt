package com.appvengers.repository.network

import android.util.Log
import com.appvengers.repository.network.model.*
import com.appvengers.utils.LogTindogs
import io.reactivex.Flowable
import io.reactivex.schedulers.Schedulers

internal class NetworkEntitiesFetcherImpl(private val networkManager: NetworkManager): NetworkEntitiesFetcher
{
    override fun createDog(userId: String, token: String, name: String, age: Double, breed: String, pureBreed: Boolean, color: String, description: String, photos: List<String>): Flowable<ResultUserJson>
    {
        return networkManager.createDog(userId, token, name, age, breed, pureBreed, color, description, photos)
    }

    override fun updateUser(firstName: String, lastName: String,  email: String, userName: String, coordinates: List<Double>?,
                            userId: String, token: String): Flowable<ResultUserJson>
    {
       return networkManager.updateUser(userId, token, firstName, lastName,email, userName, coordinates)
    }

    override fun createUser(firstName: String, lastName: String,  email: String, userName: String, password: String, photo: String): Flowable<ResultUserJson>
    {
        return networkManager.createUser(firstName, lastName, email, userName, password, photo)
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