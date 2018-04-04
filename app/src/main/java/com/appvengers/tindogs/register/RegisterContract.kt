package com.appvengers.tindogs.register

import com.appvengers.business.models.User

interface RegisterContract
{
    interface View
    {
        fun openUserProfile()
        fun setRegisterError(message: String)
        fun saveTokenAndUserId(token: String, userId: String)
    }
    interface Presenter
    {
        fun registerUser(firstName: String, lastName: String, email: String, userName: String, password: String)
    }
}