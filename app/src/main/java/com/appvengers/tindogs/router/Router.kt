package com.appvengers.tindogs.router

import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.appvengers.business.models.User
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
    }
}