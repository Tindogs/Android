package com.appvengers.repository.cache

import android.util.Log
import com.appvengers.repository.db.DAOPersistable
import com.appvengers.repository.models.*
import com.appvengers.utils.LogTindogs
import io.reactivex.Flowable
import io.reactivex.BackpressureStrategy


internal class CacheImpl(private val daoUserPersistable: DAOPersistable<UserEntityWrapper>, private val daoDogPersistable: DAOPersistable<DogEntityWrapper>): Cache
{
    override fun saveDog(allDogsEntityWrapper: List<DogEntityWrapper>): Flowable<Boolean>
    {
        return Flowable.fromCallable {
            daoDogPersistable.deleteAll()
            daoDogPersistable.insert(allDogsEntityWrapper)
        }
    }



    override fun updateUser(userEntityWrapper: UserEntityWrapper): Flowable<Boolean>
    {
        return Flowable.fromCallable {
           daoUserPersistable.update(userEntityWrapper)
        }
    }

    override fun getUser(userId: String): Flowable<UserEntityWrapper>
    {
        return Flowable.create<UserEntityWrapper>({
            val user = daoUserPersistable.query(userId)
            if (user != null)
            {
                LogTindogs("User obtenido de cache: " + user.toString(), Log.DEBUG)
                it.onNext(user)
            }
            it.onComplete()
        },BackpressureStrategy.BUFFER)

    }

    override fun saveUser(userEntityWrapper: UserEntityWrapper): Flowable<String>
    {
        return Flowable.fromCallable {
            daoUserPersistable.deleteAll()
            daoUserPersistable.insert(userEntityWrapper)
        }
    }

}