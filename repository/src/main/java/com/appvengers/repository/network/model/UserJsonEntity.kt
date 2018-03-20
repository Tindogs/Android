package com.appvengers.repository.network.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class UserJsonEntity(
         val _id: String,
        @SerializedName("first_name")
        val firstName: String?,
        @SerializedName("last_name")
        val lastName: String?,
        val phone: String?,
        val mobilePhone: String?,
        val email: String?,
        @SerializedName("username")
        val userName: String,
        val coordinates: List<Double>?,
        val dogs: List<DogJsonEntity>?)

