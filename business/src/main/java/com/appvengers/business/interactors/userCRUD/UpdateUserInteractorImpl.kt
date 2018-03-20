package com.appvengers.business.interactors.userCRUD

import com.appvengers.business.mappers.map
import com.appvengers.business.models.User
import com.appvengers.repository.Repository

class UpdateUserInteractorImpl(private val repository: Repository): UpdateUserInteractor
{
    override fun execute(user: User, token: String, success: (user: User) -> Unit, error: (message: String) -> Unit)
    {
        repository.updateUser(user.map(), token,
                success =
                {
                    success(it.map())
                },
                error = {
                    error(it)
                })
    }
}
