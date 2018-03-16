package com.appvengers.business.interactors.userCRUD

import com.appvengers.business.models.User
import com.appvengers.repository.Repository
import com.appvengers.repository.models.UserEntityWrapper
import com.appvengers.business.mappers.map

class GetUserInteractorImpl(private val repository: Repository): GetUserInteractor
{
    override fun execute(email: String, password: String, success: (user: User) -> Unit, error: (message: String) -> Unit)
    {
        repository.getUser(email, password,
                success = {userEntity: UserEntityWrapper ->
                    success(userEntity.map())
                },
                error = {message: String ->
                    error(message)
                })
    }

    override fun execute(userId: String, success: (user: User) -> Unit, error: (message: String) -> Unit)
    {
        repository.getUser(userId,
                success = {userEntity: UserEntityWrapper ->
                    success(userEntity.map())
                },
                error = {message: String ->
                    error(message)
                })
    }
}