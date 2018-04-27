package com.appvengers.repository.network
import com.appvengers.repository.models.QueryEntityWrapper
import com.appvengers.repository.network.model.*
import io.reactivex.Flowable

interface NetworkEntitiesFetcher
{
    fun getUser(email: String, password: String): Flowable<ResultUserJson>
    fun getUserById(userId: String, token: String): Flowable<ResultUserJson>
    fun createUser(firstName: String, lastName: String, email: String, userName: String, password: String, photo: String): Flowable<ResultUserJson>
    fun updateUser(firstName: String, lastName: String,email: String, userName: String, coordinates: List<Double>?,
                   userId: String, token: String): Flowable<ResultUserJson>
    fun createDog(  userId: String,
                    token: String,
                    name: String,
                    age: Double,
                    breed: String,
                    pureBreed: Boolean,
                    color: String,
                    description: String,
                    photos: List<String>,
                    queryAge: Double,
                    queryMaxKms: Double,
                    queryReproductive: Boolean,
                    queryBreed: String): Flowable<ResultUserJson>

    fun getDogsList(userId: String, dogId: String, token: String): Flowable<ResultDogsJson>

    fun putNewDogLike(userId: String,dogLocalId: String, dogWhoLikes: String, valueLike: Boolean, token: String): Flowable<ResultLikesJson>

    fun getDogDetail(userId: String, dogId: String, token: String) : Flowable<ResultDogDetailJson>
}