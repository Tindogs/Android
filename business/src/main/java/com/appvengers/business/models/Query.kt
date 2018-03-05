package com.appvengers.business.models

data class Query(
        val ageFrom: Double,
        val ageTo: Double,
        val maxKms: Double,
        val reproductive: Boolean,
        val breed: String
)