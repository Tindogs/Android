
package com.appvengers.repository.db

import com.appvengers.repository.models.UserEntity
import com.appvengers.repository.models.UserEntityDao
import org.greenrobot.greendao.AbstractDao

//TODO("Gestion de errores")
internal class UserDAO(private val userEntityDAO: UserEntityDao): DAOPersistable<UserEntity>
{
    override fun insert(element: UserEntity): Long
    {
        element._id = userEntityDAO.insert(element)
        return element._id

    }

    override fun insert(elements: List<UserEntity>): Boolean
    {
        userEntityDAO.insertInTx(elements)
        return true
    }

    override fun update(element: UserEntity): Boolean
    {
        userEntityDAO.update(element)
        return true
    }

    override fun delete(element: UserEntity): Boolean
    {
        userEntityDAO.delete(element)
        return true
    }

    override fun delete(databaseID: Long): Boolean
    {
        userEntityDAO.deleteByKey(databaseID)
        return true
    }

    override fun deleteAll(): Boolean
    {
        userEntityDAO.deleteAll()
        return true
    }

    override fun query(databaseID: Long): UserEntity?
    {
        return userEntityDAO.load(databaseID)
    }

    override fun queryAll(): List<UserEntity>
    {
        return userEntityDAO.loadAll()
    }
}
