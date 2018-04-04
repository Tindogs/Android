package com.appvengers.repository

import android.util.Log
import com.appvengers.repository.cache.Cache
import com.appvengers.repository.mappers.map
import com.appvengers.repository.mappers.mapDogs
import com.appvengers.repository.models.DogEntityWrapper
import com.appvengers.repository.models.UserEntityWrapper
import com.appvengers.repository.network.NetworkEntitiesFetcher
import com.appvengers.repository.network.model.ResultUserJson
import com.appvengers.utils.LogTindogs
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException

internal class RepositoryImpl(private val cache: Cache, private val networkEntitiesFetcher: NetworkEntitiesFetcher): Repository
{
    override fun createDog(
            userId: String,
            token: String,
            name: String,
            age: Double,
            breed: String,
            pureBreed: Boolean,
            color: String,
            description: String,
            photos: List<String>,
            success: (dogs: List<DogEntityWrapper>) -> Unit,
            error: (message: String) -> Unit
    )
    {
        networkEntitiesFetcher.createDog(userId, token, name, age, breed, pureBreed, color, description, photos)
                .subscribeOn(Schedulers.io())
                .doOnNext {
                    cache.saveDog(it.mapDogs())
                            .subscribe({},
                                    {
                                        LogTindogs("Error al guargar los perros: ${it.localizedMessage}", Log.ERROR)
                                    })
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            LogTindogs("networkEntitiesFetcher (register dog): ${it.toString()}", Log.DEBUG)
                            success(it.mapDogs())
                        },
                        {
                            error(it.localizedMessage)
                        })
    }

    override fun updateUser(user: UserEntityWrapper, token: String, success: (user: UserEntityWrapper) -> Unit, error: (message: String) -> Unit)
    {
        cache.updateUser(user)
                .subscribeOn(Schedulers.io())
                .doOnNext {
                    val coordinates = if (user.coordinates == null)
                    {
                        null
                    }
                    else
                    {
                        listOf(user.coordinates.first, user.coordinates.second)
                    }
                    networkEntitiesFetcher.updateUser(user.firstName, user.lastName, user.email, user.userName,
                        coordinates,user._id, token)
                        .subscribe ({ LogTindogs("Usuario actializado en API. $user", Log.INFO) },
                                { LogTindogs("Error al actualizar el usuario API: ${it.localizedMessage}", Log.ERROR)})
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { result: Boolean ->
                            if (result)
                            {
                                success(user)
                            }
                            else
                            {
                                error("Error al actualizar el usuario")
                            }
                        },
                        {
                            when(it::class.java)
                            {
                                HttpException::class.java ->
                                    if ((it as HttpException).code() == 401)
                                    {error("Las credenciales no son correctas")}
                                    else{error(it.localizedMessage)}

                                else -> error(it.localizedMessage)
                            }

                        })
    }

    override fun createUser(
            firstName: String,
            lastName: String,
             email: String,
            userName: String,
            password: String,
            success: (user: UserEntityWrapper, token: String) -> Unit,
            error: (message: String) -> Unit
    )
    {
        networkEntitiesFetcher.createUser(firstName, lastName, email, userName, password)
                .subscribeOn(Schedulers.io())
                .doOnNext {
                    cache.saveUser(it.map()).subscribe({}, {
                        LogTindogs("Error al guargar el usuario: ${it.localizedMessage}", Log.ERROR)
                    })
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {
                            LogTindogs("networkEntitiesFetcher (register user): ${it.toString()}", Log.DEBUG)
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
                        LogTindogs("Error al guargar el usuario: ${it.localizedMessage}", Log.DEBUG)
                    })
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        { userJsonEntity: ResultUserJson ->
                            LogTindogs("networkEntitiesFetcher: ${userJsonEntity.toString()}", Log.DEBUG)
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
                           LogTindogs("User obtenido: ${it.toString()}", Log.DEBUG)
                            success(it)
                        },
                        {
                            LogTindogs("Error: ${it.localizedMessage}", Log.ERROR)
                            error(it.localizedMessage)
                        })

    }
}