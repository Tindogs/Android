
package com.appvengers.repository.db

import android.util.Log
import com.appvengers.repository.mappers.map
import com.appvengers.repository.mappers.mapToPhotoEntity
import com.appvengers.repository.models.DaoSession
import com.appvengers.repository.models.UserEntityWrapper
import org.greenrobot.greendao.rx.RxDao
import org.greenrobot.greendao.rx.RxQuery

//TODO("Gestion de errores")
internal class UserDAO(private val session: DaoSession): DAOPersistable<UserEntityWrapper>
{
    private val userEntityDAO by lazy { session.userEntityDao }
    private val dogEntityDao by lazy { session.dogEntityDao }
    private val dogLikeEntityDao by lazy { session.dogLikeEntityDao }
    private val photosEntityDao by lazy { session.photosEntityDao }
    private val queryEntityDao by lazy { session.queryEntityDao }

    override fun insert(element: UserEntityWrapper): Long
    {
        val db = session.database
        db.beginTransaction()
        try
        {
            userEntityDAO.insert(element.map())
            insertOrReplaceDogsFromUser(element)
            db.setTransactionSuccessful()
        } catch (ex: Exception)
        {
            Log.e("Tindogs", "Error al insertar usuario(" + element.toString() + ") de la base de datos: " + ex.localizedMessage)
            return -1
        }
        finally
        {
            db.endTransaction()
        }

        return element._id

    }

    override fun update(element: UserEntityWrapper): Boolean
    {
        val db = session.database
        db.beginTransaction()
        try
        {
            userEntityDAO.update(element.map())
            insertOrReplaceDogsFromUser(element)
            db.setTransactionSuccessful()
            return true
        } catch (ex: Exception)
        {
            Log.e("Tindogs", "Error al insertar usuario(" + element.toString() + ") de la base de datos: " + ex.localizedMessage)
            return false
        }
        finally
        {
            db.endTransaction()
        }
    }

    override fun delete(element: UserEntityWrapper): Boolean
    {
        return try
        {
            userEntityDAO.delete(element.map())
            true
        }
        catch (e: Exception)
        {
            Log.e("Tindogs", "Error al borrar usuario (" + element.toString() + ") de la base de datos: " + e.localizedMessage)
            false
        }
    }

    override fun delete(databaseID: Long): Boolean
    {
        return try
        {
            userEntityDAO.deleteByKey(databaseID)
            true
        }
        catch (e: Exception)
        {
            Log.e("Tindogs", "Error al borrar usuario (" + databaseID.toString() + ") de la base de datos: " + e.localizedMessage)
            false
        }
    }

    override fun deleteAll(): Boolean
    {
        return try
        {
            userEntityDAO.deleteAll()
            true
        }
        catch (e: Exception)
        {
            Log.e("Tindogs", "Error al borrar todos los usuarios de la base de datos: " + e.localizedMessage)
            false
        }
    }

    override fun query(databaseID: Long): UserEntityWrapper?
    {
        return userEntityDAO.load(databaseID).map()
    }

    override fun queryAll(): List<UserEntityWrapper>
    {
        return userEntityDAO.loadAll().map { it.map()!! }
    }

    private fun insertOrReplaceDogsFromUser(userEntityWrapper: UserEntityWrapper)
    {
        if (userEntityWrapper.dogs.count() > 0)
        {
            userEntityWrapper.dogs.forEach {

                val dogId = dogEntityDao.insertOrReplace(it.map(userEntityWrapper._id))

                it.likesFromOthers.forEach{
                    dogLikeEntityDao.insertOrReplace(it.map(dogId))
                }

                it.photos.forEach {
                    photosEntityDao.insertOrReplace(it.mapToPhotoEntity(dogId))
                }
                queryEntityDao.insertOrReplace(it.query.map(dogId))

            }
        }
    }
}
