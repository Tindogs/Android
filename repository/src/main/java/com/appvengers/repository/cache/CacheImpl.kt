package com.appvengers.repository.cache

import com.appvengers.repository.db.DAOPersistable
import com.appvengers.repository.models.UserEntity

internal class CacheImpl(private val daoPersistable: DAOPersistable<UserEntity>): Cache
{
    override fun getUser(userId: Long, success: (user: UserEntity) -> Unit, error: (message: String) -> Unit)
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
}