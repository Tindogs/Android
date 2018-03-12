package com.appvengers.repository.network.model

data class UserJsonEntity( val _id: Long,
                           val firstName: String,
                           val lastName:String,
                           val phone: String,
                           val mobilePhone: String,
                           val email: String,
                           val userName: String,
                           val coordinates: Pair<Double, Double>,
                           val dogs: List<DogJsonEntity>)
{
}