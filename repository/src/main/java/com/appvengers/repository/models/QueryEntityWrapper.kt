package com.appvengers.repository.models

data class QueryEntityWrapper(
        val ageFrom: Double,
        val ageTo: Double,
        val maxKms: Double,
        val reproductive: Boolean,
        val breed: String
)