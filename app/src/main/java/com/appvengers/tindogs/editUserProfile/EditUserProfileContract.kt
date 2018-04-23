package com.appvengers.tindogs.editUserProfile

import android.net.Uri
import com.appvengers.business.models.User


interface EditUserProfileContract {

    interface View
    {
        fun bindUserData(user:User)
        fun setErrorView(msg: String)
        fun setResultOk()
        fun setResultCancel()

    }
    interface Presenter
    {
        fun getUser(userId: String, token: String)
        fun updateUser(firstName: String, lastName: String, userName: String, photoUrl: Uri, token: String)
    }

}