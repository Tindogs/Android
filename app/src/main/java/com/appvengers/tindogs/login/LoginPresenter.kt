package com.appvengers.tindogs.login


import android.util.Log
import com.appvengers.business.interactors.userCRUD.GetUserInteractor
import com.appvengers.business.models.User
import com.appvengers.utils.LogTindogs

class LoginPresenter(private val view: LoginContract.View, private val getUserInteractor: GetUserInteractor): LoginContract.Presenter
{
    override fun getUserById(userId: String, token: String)
    {
        getUserInteractor.execute(userId, token,
                success = {user: User ->
                    LogTindogs("User got (userId): ", Log.DEBUG)
                    view.openUserProfile()
                },
                error = {})
    }

    override fun goToRegister()
    {
        view.openRegisterForm()
    }

    override fun getUser(email: String, password: String)
    {
        LogTindogs("Get user, name password: ", Log.DEBUG)
       getUserInteractor.execute(email, password,
                success = {user: User, token: String ->
                    LogTindogs("User got (name, password): ", Log.DEBUG)
                    view.saveTokenAndUserId(token, user._id)
                    view.openUserProfile()
                },
                error = {message ->
                    view.setLoginError(message)
                })
    }


}