package com.appvengers.repository.network.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class DogJsonEntity(
        val _id: String,
        val name: String?,
        val age: Double?,
        val breed: String?,
        val pureBreed: Boolean?,
        val color: String?,
        val description: String?,
        val photos: List<String>?,
        val query: QueryJsonEntity?,
        @SerializedName("likes_from_others")
        val likesFromOthers: List<DogLikeJsonEntity>?
)