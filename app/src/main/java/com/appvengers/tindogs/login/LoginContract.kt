package com.appvengers.tindogs.login

import com.appvengers.business.models.User


interface LoginContract
{
    interface View
    {
        fun saveTokenAndUserId(token: String, userId: String)
        fun openRegisterForm()
        fun openUserProfile()
        fun setLoginError(message: String)
    }

    interface Presenter
    {
        fun getUser(email: String, password: String)
        fun getUserById(userId: String, token: String)
        fun goToRegister()
    }
}