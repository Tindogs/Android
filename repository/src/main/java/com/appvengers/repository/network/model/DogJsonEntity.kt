package com.appvengers.repository.network.model

data class DogJsonEntity(
        val _id: Long,
        val name: String,
        val age: Double,
        val breed: String,
        val pureBreed: Boolean,
        val color: String,
        val description: String,
        val photos: List<String>,
        val query: QueryJsonEntity,
        val likesFromOthers: List<DogLikeJsonEntity>
)