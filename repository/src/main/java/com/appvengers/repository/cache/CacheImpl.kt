package com.appvengers.repository.cache

import com.appvengers.repository.db.DAOPersistable
import com.appvengers.repository.models.*

internal class CacheImpl(private val daoPersistable: DAOPersistable<UserEntityWrapper>): Cache
{

    override fun getUser(userId: Long, success: (user: UserEntityWrapper) -> Unit, error: (message: String) -> Unit)
    {
        val user = daoPersistable.query(userId)
        if (user == null)
        {
            error("No se ha encontrado el usuario")
        }
        else
        {
            success(user)
        }
    }

    override fun saveUser(userEntityWrapper: UserEntityWrapper, success: () -> Unit, error: (message: String) -> Unit)
    {
        if (daoPersistable.insert(userEntityWrapper) == -1L)
        {
            error("Error al insertar")
        }
        else
        {
            success()
        }
    }

}