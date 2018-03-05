package com.appvengers.tindogs

import com.appvengers.business.models.User


interface LoginContract
{
    interface View
    {
        fun openRegisterForm()
        fun openUserProfile(user: User)
    }

    interface Presenter
    {
        fun getUser(userName: String, password: String)
    }
}