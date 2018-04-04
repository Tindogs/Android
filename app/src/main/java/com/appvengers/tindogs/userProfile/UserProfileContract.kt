package com.appvengers.tindogs.userProfile

import com.appvengers.business.models.Dog

interface UserProfileContract
{
    interface View
    {
        fun renderUser(userName: String)
        fun setUserProfileError(message: String)
        fun getLocation()
        fun onUserNotFound()
        fun setupDogList(dogs: List<Dog>)
    }
    interface Presenter {
        fun updateCoordinates(latitude: Double, longitude: Double, token: String)
        fun getUser(userId: String, token: String)

    }
}