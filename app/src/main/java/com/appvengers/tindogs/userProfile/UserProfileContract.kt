package com.appvengers.tindogs.userProfile

interface UserProfileContract
{
    interface View
    {
        fun renderUser(userName: String)
        fun setUserProfileError(message: String)
        fun getLocation()
    }
    interface Presenter {
        fun updateCoordinates(latitude: Double, longitude: Double, token: String)
        fun getUser(userId: String, token: String)
    }
}