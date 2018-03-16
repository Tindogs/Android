package com.appvengers.business.interactors.userCRUD

import com.appvengers.business.models.User

class GetUserIteractorFake: GetUserInteractor
{
    override fun execute(userId: String, success: (user: User) -> Unit, error: (message: String) -> Unit)
    {
        success( User("abc", "Pepito", "Gomez", "9999999", "9999999", "fulano@gmail.com", "Pepito1", Pair(45.0, 34.0), listOf()))
    }

    override fun execute(email: String, password: String, success: (user: User) -> Unit, error: (message: String) -> Unit)
    {
        success( User("Abd", "Pepito", "Gomez", "9999999", "9999999", "fulano@gmail.com", "Pepito1", Pair(45.0, 34.0), listOf()))
        //error("Ha habido un error")
    }
}