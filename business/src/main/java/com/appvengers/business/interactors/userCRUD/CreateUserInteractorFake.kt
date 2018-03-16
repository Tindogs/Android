package com.appvengers.business.interactors.userCRUD

import com.appvengers.business.models.User

class CreateUserInteractorFake: CreateUserInteractor
{
    override fun execute(firstName: String,
                         lastName: String,
                         phone: String,
                         mobilePhone: String,
                         email: String,
                         userName: String,
                         password: String,
                         success: (user: User) -> Unit,
                         error: (message: String) -> Unit)
    {
        success( User("abc", "Pepito", "Gomez", "9999999", "9999999", "fulano@gmail.com", "Pepito1", Pair(45.0, 34.0), listOf()))
        //error("Ha habido un error")
    }
}