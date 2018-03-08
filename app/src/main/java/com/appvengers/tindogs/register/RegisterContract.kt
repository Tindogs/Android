package com.appvengers.tindogs.register

import com.appvengers.business.models.User

interface RegisterContract
{
    interface View
    {
        fun openUserProfile(user: User)
        fun setRegisterError(message: String)
    }
    interface Presenter
    {
        fun registerUser(firstName: String, lastName: String, phone: String, mobilePhone: String, email: String, userName: String, password: String)
    }
}