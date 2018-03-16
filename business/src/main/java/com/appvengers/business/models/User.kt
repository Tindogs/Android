package com.appvengers.business.models

data class User(
        val _id: String,
        val firstName: String,
        val lastName:String,
        val phone: String,
        val mobilePhone: String,
        val email: String,
        val userName: String,
        val coordinates: Pair<Double, Double>?,
        val dogs: List<Dog>
)