package com.appvengers.repository.models

data class DogEntityWrapper(
        val _id: Long,
        val name: String,
        val age: Double,
        val breed: String,
        val pureBreed: Boolean,
        val color: String,
        val description: String,
        val photos: List<String>,
        val query: QueryEntityWrapper,
        val likesFromOthers: List<DogLikeEntityWrapper>,
        val userId: Long
)
