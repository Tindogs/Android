package com.appvengers.repository.models

data class UserEntity( val _id: Long,
                  val firstName: String,
                  val lastName:String,
                  val phone: String,
                  val mobilePhone: String,
                  val email: String,
                  val userName: String,
                  val coordinates: Pair<Double, Double>,
                  val dogs: List<DogEntity>)
