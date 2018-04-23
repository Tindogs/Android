package com.appvengers.tindogs.router

import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.appvengers.business.models.Dog
import com.appvengers.business.models.User
import com.appvengers.tindogs.dogRegister.DogRegisterActivity
import com.appvengers.tindogs.editUserProfile.EditUserProfileActivity
import com.appvengers.tindogs.login.MainActivity
import com.appvengers.tindogs.match.MatchActivity
import com.appvengers.tindogs.register.RegisterActivity
import com.appvengers.tindogs.userProfile.UserProfileActivity

class Router
{
    companion object
    {
        fun navigateToRegisterForm(activity: AppCompatActivity)
        {
            activity.startActivity(RegisterActivity.intent(activity))
        }

        fun navigateToUserProfile(activity: AppCompatActivity)
        {
            activity.startActivity(UserProfileActivity.intent(activity))
        }

        fun navigateToDogRegister(activity: AppCompatActivity)
        {
            activity.startActivity(DogRegisterActivity.intent(activity))
        }

        fun navigateToLogin(activity: AppCompatActivity)
        {
            activity.startActivity(MainActivity.intent(activity))
        }

        fun navigateToDogMatch(activity: AppCompatActivity, dog: Dog)
        {
            activity.startActivity(MatchActivity.intent(activity,dog))
        }

        fun navigateToEditUserProfile(activity: AppCompatActivity) {
            val intent = EditUserProfileActivity.intent(activity)
            activity.startActivityForResult(intent, EditUserProfileActivity.REQUEST_NUMBER)
        }
    }
}