package com.appvengers.repository.network.model

import java.util.*

data class DogJsonEntity(
        val _id: String,
        val name: String,
        val age: Double?,
        val breed: String?,
        val pureBreed: Boolean?,
        val color: String?,
        val description: String?,
        val photos: List<String>?,
        val query: QueryJsonEntity?,
        val likesFromOthers: List<DogLikeJsonEntity>?
)