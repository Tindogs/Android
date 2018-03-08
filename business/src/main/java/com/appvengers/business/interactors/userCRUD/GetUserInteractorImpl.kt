package com.appvengers.business.interactors.userCRUD

import com.appvengers.business.mappers.map
import com.appvengers.business.models.User
import com.appvengers.repository.Repository
import com.appvengers.repository.models.UserEntity

class GetUserInteractorImpl(private val repository: Repository): GetUserInteractor
{
    override fun execute(username: String, password: String, success: (user: User) -> Unit, error: (message: String) -> Unit)
    {
        repository.getUser(username, password,
                success = {userEntity: UserEntity ->
                    success(userEntity.map())
                },
                error = {message: String ->
                    error(message)
                })
    }

    override fun execute(userId: Long, success: (user: User) -> Unit, error: (message: String) -> Unit)
    {
        repository.getUser(userId,
                success = {userEntity: UserEntity ->
                    success(userEntity.map())
                },
                error = {message: String ->
                    error(message)
                })
    }
}