package com.appvengers.repository

import com.appvengers.repository.models.DogEntityWrapper
import com.appvengers.repository.models.UserEntityWrapper
import com.appvengers.repository.network.model.MatchResultEntity

interface Repository
{
    fun getUser(email: String, password: String, success: (user: UserEntityWrapper, token: String) -> Unit, error: (message: String) -> Unit)
    fun getUser(userId: String, token: String,  success: (user: UserEntityWrapper) -> Unit, error: (message: String) -> Unit)
    fun createUser(
            firstName: String,
            lastName: String,
            email: String,
            userName: String,
            password: String,
            photo: String,
            success: (user: UserEntityWrapper, token: String) -> Unit,
            error: (message: String) -> Unit
    )
    fun updateUser(user: UserEntityWrapper, token: String, success: (user: UserEntityWrapper) -> Unit, error: (message: String) -> Unit)
    fun createDog(userId: String,
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
                  queryBreed: String,
                  success: (dogs: List<DogEntityWrapper>) -> Unit,
                  error: (message: String) -> Unit)

    fun newDogLike(userId: String, dog:DogEntityWrapper, localDogId: String, likeValue: Boolean, token: String, success: (resultMatch: MatchResultEntity) -> Unit, error: (message: String) -> Unit)

    fun getDogList(userId: String, dogId: String, token: String, success: (MutableList<DogEntityWrapper>) -> Unit, error: (message: String) -> Unit)
}