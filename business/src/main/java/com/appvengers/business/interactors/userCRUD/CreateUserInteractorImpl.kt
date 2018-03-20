package com.appvengers.business.interactors.userCRUD

import com.appvengers.business.mappers.map
import com.appvengers.business.models.User
import com.appvengers.repository.Repository
import com.appvengers.repository.models.UserEntityWrapper

class CreateUserInteractorImpl(private val repository: Repository): CreateUserInteractor
{
    override fun execute(firstName: String, lastName: String, phone: String, mobilePhone: String, email: String, userName: String, password: String, success: (user: User, token: String) -> Unit, error: (message: String) -> Unit)
    {
        repository.createUser(firstName, lastName, phone, mobilePhone, email, userName, password,
                success = { userEntity: UserEntityWrapper, token: String ->
                    success(userEntity.map(), token)
                },
                error = {message: String ->
                    error(message)
                })
    }

}