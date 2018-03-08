package com.appvengers.repository.models

data class DogEntity(
        val _id: Long,
        val name: String,
        val age: Double,
        val breed: String,
        val pureBreed: Boolean,
        val color: String,
        val description: String,
        val photos: List<String>,
        val query: QueryEntity,
        val likesFromOthers: List<DogLikeEntity>
)
