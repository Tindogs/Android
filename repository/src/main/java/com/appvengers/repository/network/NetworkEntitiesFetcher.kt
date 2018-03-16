package com.appvengers.repository.network

import com.appvengers.repository.network.model.UserJsonEntity
import io.reactivex.Flowable

interface NetworkEntitiesFetcher
{
    fun getUser(email: String, password: String): Flowable<UserJsonEntity>
    fun getUser(userId: String): Flowable<UserJsonEntity>
}