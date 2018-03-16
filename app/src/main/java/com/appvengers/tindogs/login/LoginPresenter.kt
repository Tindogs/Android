package com.appvengers.tindogs.login


import com.appvengers.business.interactors.userCRUD.GetUserInteractor

class LoginPresenter(private val view: LoginContract.View, private val getUserInteractor: GetUserInteractor): LoginContract.Presenter
{
    override fun goToRegister()
    {
        view.openRegisterForm()
    }

    override fun getUser(email: String, password: String)
    {
       getUserInteractor.execute(email, password,
                success = {user ->
                    view.openUserProfile(user)
                },
                error = {message ->
                    view.setLoginError(message)
                })
    }


}