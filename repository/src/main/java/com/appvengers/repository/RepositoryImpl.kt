package com.appvengers.repository

import android.util.Log
import com.appvengers.repository.cache.Cache
import com.appvengers.repository.mappers.map
import com.appvengers.repository.models.UserEntityWrapper
import com.appvengers.repository.network.NetworkEntitiesFetcher
import com.appvengers.repository.network.model.ResultUserJson
import com.appvengers.utils.LogTindogs
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

internal class RepositoryImpl(private val cache: Cache, private val networkEntitiesFetcher: NetworkEntitiesFetcher): Repository
{
    override fun updateUser(user: UserEntityWrapper, token: String, success: (user: UserEntityWrapper) -> Unit, error: (message: String) -> Unit)
    {
        cache.updateUser(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {

                }
    }

    override fun createUser(
            firstName: String,
            lastName: String,
            phone: String,
            mobilePhone: String,
            email: String,
            userName: String,
            password: String,
            success: (user: UserEntityWrapper, token: String) -> Unit,
            error: (message: String) -> Unit
    )
    {
        networkEntitiesFetcher.createUser(firstName, lastName, phone, mobilePhone, email, userName, password)
                .subscribeOn(Schedulers.io())
                .doOnNext {
                    cache.saveUser(it.map()).subscribe({}, {
                        LogTindogs("Error al guargar el usuario: " + it.localizedMessage, Log.ERROR)
                    })
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            LogTindogs("networkEntitiesFetcher (register user): " + it.toString(), Log.DEBUG)
                            success(it.map(), it.token)
                        },
                        {
                            error(it.localizedMessage)
                        })
    }

    override fun getUser(email: String, password: String, success: (user: UserEntityWrapper, token: String) -> Unit, error: (message: String) -> Unit)
    {
        networkEntitiesFetcher.getUser(email, password)
                .subscribeOn(Schedulers.io())
                .doOnNext {
                    LogTindogs("save user: " + it.toString(), Log.DEBUG)

                    cache.saveUser(it.map()).subscribe({}, {
                        LogTindogs("Error al guargar el usuario: " + it.localizedMessage, Log.DEBUG)
                    })
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { userJsonEntity: ResultUserJson ->
                            LogTindogs("networkEntitiesFetcher: " + userJsonEntity.toString(), Log.DEBUG)
                            success(userJsonEntity.map(), userJsonEntity.token)
                        },
                        {
                            error(it.localizedMessage)
                        })
    }


    override fun getUser(userId: String, token: String,  success: (user: UserEntityWrapper) -> Unit, error: (message: String) -> Unit)
    {
        cache.getUser(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .switchIfEmpty { networkEntitiesFetcher.getUser(userId, token) }
                .subscribe (
                        {
                           LogTindogs("User obtenido: " + it.toString(), Log.DEBUG)
                            success(it)
                        },
                        {
                            LogTindogs("Error: " + it.localizedMessage, Log.ERROR)
                            error(it.localizedMessage)
                        })

    }
}