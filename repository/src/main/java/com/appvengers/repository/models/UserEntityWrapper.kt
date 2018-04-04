package com.appvengers.repository.models

data class UserEntityWrapper(val _id: String,
                             val firstName: String,
                             val lastName:String,
                             val email: String,
                             val userName: String,
                             val coordinates: Pair<Double, Double>?,
                             val dogs: List<DogEntityWrapper>)
