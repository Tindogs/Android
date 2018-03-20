package com.appvengers.tindogs.register

import com.appvengers.business.interactors.userCRUD.CreateUserInteractor
import com.appvengers.business.models.User


class RegisterPresenter(private val view: RegisterContract.View, private val createUserInteractor: CreateUserInteractor): RegisterContract.Presenter
{
    override fun registerUser(firstName: String, lastName: String, phone: String, mobilePhone: String, email: String, userName: String, password: String)
    {
        createUserInteractor.execute(firstName, lastName, phone, mobilePhone, email, userName, password,
                success = {user: User, token: String ->
                    view.saveTokenAndUserId(token, user._id)
                    view.openUserProfile()
                },
                error = { message ->
                    view.setRegisterError(message)
                })
    }

}