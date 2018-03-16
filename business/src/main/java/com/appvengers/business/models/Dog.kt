package com.appvengers.business.models

data class Dog(
        val _id: String,
        val name: String,
        val age: Double,
        val breed: String,
        val pureBreed: Boolean,
        val color: String,
        val description: String,
        val photos: List<String>,
        val query: Query,
        val likesFromOthers: List<DogLike>
)
