package com.appvengers.repository.cache

import android.util.Log
import com.appvengers.repository.db.DAOPersistable
import com.appvengers.repository.models.*
import com.appvengers.utils.LogTindogs
import io.reactivex.Flowable
import io.reactivex.BackpressureStrategy
import io.reactivex.FlowableOnSubscribe


internal class CacheImpl(private val daoPersistable: DAOPersistable<UserEntityWrapper>): Cache
{
    override fun updateUser(userEntityWrapper: UserEntityWrapper): Flowable<Boolean>
    {
        return Flowable.fromCallable {
            daoPersistable.deleteAll()
            daoPersistable.update(userEntityWrapper)
        }
    }

    override fun getUser(userId: String): Flowable<UserEntityWrapper>
    {
        return Flowable.create<UserEntityWrapper>({
            val user = daoPersistable.query(userId)
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
            daoPersistable.deleteAll()
            daoPersistable.insert(userEntityWrapper)
        }
    }

}