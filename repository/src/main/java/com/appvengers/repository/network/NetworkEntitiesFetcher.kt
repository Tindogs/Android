package com.appvengers.repository.network
import com.appvengers.repository.network.model.ResultUserJson
import com.appvengers.repository.network.model.UserJsonEntity
import io.reactivex.Flowable

interface NetworkEntitiesFetcher
{
    fun getUser(email: String, password: String): Flowable<ResultUserJson>
    fun getUserById(userId: String, token: String): Flowable<ResultUserJson>
    fun createUser(firstName: String, lastName: String, phone: String, mobilePhone: String, email: String, userName: String, password: String): Flowable<ResultUserJson>
}