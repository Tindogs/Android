package com.appvengers.repository.network.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class UserJsonEntity(
        @SerializedName("userid")
        val _id: String,
        val firstName: String?,
        val lastName: String?,
        val phone: String?,
        val mobilePhone: String?,
        val email: String?,
        @SerializedName("username")
        val userName: String,
        val token: String,
        val coordinates: Pair<Double, Double>?,
        val dogs: List<DogJsonEntity>?)
