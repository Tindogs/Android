package com.appvengers.repository.cache

import com.appvengers.repository.models.*
import io.reactivex.Flowable

interface Cache
{
    fun getUser(userId: String): Flowable<UserEntityWrapper>
    fun saveUser(userEntityWrapper: UserEntityWrapper): Flowable<String>
    fun updateUser(userEntityWrapper: UserEntityWrapper): Flowable<Boolean>
}