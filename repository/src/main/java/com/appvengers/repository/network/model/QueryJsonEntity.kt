package com.appvengers.repository.network.model

data class QueryJsonEntity(
        val ageFrom: Double,
        val ageTo: Double,
        val maxKms: Double,
        val reproductive: Boolean,
        val breed: String
)