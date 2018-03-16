package com.appvengers.repository

import android.util.Log
import com.appvengers.repository.cache.Cache
import com.appvengers.repository.mappers.map
import com.appvengers.repository.models.UserEntityWrapper
import com.appvengers.repository.network.NetworkEntitiesFetcher
import com.appvengers.repository.network.model.UserJsonEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

internal class RepositoryImpl(private val cache: Cache, private val networkEntitiesFetcher: NetworkEntitiesFetcher): Repository
{
    override fun getUser(email: String, password: String, success: (user: UserEntityWrapper) -> Unit, error: (message: String) -> Unit)
    {
        networkEntitiesFetcher.getUser(email, password)
                .subscribeOn(Schedulers.io())
                .doOnNext {
                    Log.d("Tindogs", "save user: " + it.toString())
                    Log.d("Tindogs", "Map user: " + it.map().toString())
                    cache.saveUser(it.map()).subscribe({},{
                        Log.e("Tindogs", "Error al guargar el usuario: " + it.localizedMessage)
                    })
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ userJsonEntity: UserJsonEntity ->
                    Log.d("Tindogs", "networkEntitiesFetcher: " + userJsonEntity.toString())
                    success(userJsonEntity.map())
                })
    }


    override fun getUser(userId: String, success: (user: UserEntityWrapper) -> Unit, error: (message: String) -> Unit)
    {
        cache.getUser(userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .switchIfEmpty { networkEntitiesFetcher.getUser(userId) }
                .subscribe (
                        {
                            Log.d("Tingogs", "User obtenido: " + it.toString() )
                            success(it)
                        },
                        {
                            Log.d("Tingogs", "Error: " + it.localizedMessage )
                            error(it.localizedMessage)
                        })

    }

    private fun populateCache(success: (user: UserEntityWrapper) -> Unit, error: (message: String) -> Unit)
    {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}