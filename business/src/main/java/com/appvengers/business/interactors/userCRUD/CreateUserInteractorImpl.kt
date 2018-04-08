package com.appvengers.business.interactors.userCRUD

import com.appvengers.business.mappers.map
import com.appvengers.business.models.User
import com.appvengers.repository.Repository
import com.appvengers.repository.models.UserEntityWrapper

class CreateUserInteractorImpl(private val repository: Repository): CreateUserInteractor
{
    override fun execute(firstName: String, lastName: String, email: String, userName: String, password: String, photo: String, success: (user: User, token: String) -> Unit, error: (message: String) -> Unit)
    {
        repository.createUser(firstName, lastName, email, userName, password,photo,
                success = { userEntity: UserEntityWrapper, token: String ->
                    success(userEntity.map(), token)
                },
                error = {message: String ->
                    error(message)
                })
    }

}