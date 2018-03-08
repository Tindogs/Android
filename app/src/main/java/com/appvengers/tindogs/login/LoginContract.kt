package com.appvengers.tindogs.login

import com.appvengers.business.models.User


interface LoginContract
{
    interface View
    {
        fun openRegisterForm()
        fun openUserProfile(user: User)
        fun setLoginError(message: String)
    }

    interface Presenter
    {
        fun getUser(userName: String, password: String)
        fun goToRegister()
    }
}