package com.appvengers.tindogs.router

import android.support.v7.app.AppCompatActivity
import com.appvengers.business.models.User
import com.appvengers.tindogs.register.RegisterActivity

class Router
{
    companion object
    {
        fun navigateToRegisterForm(activity: AppCompatActivity)
        {
            activity.startActivity(RegisterActivity.intent(activity))
        }

        fun navigateToUserProfile(activity: AppCompatActivity, user: User)
        {

        }
    }
}