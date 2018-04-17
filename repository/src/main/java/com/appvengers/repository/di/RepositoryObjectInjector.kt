package com.appvengers.repository.di

import android.content.Context
import com.appvengers.repository.BuildConfig
import com.appvengers.repository.Repository
import com.appvengers.repository.RepositoryImpl
import com.appvengers.repository.cache.Cache
import com.appvengers.repository.cache.CacheImpl
import com.appvengers.repository.db.*
import com.appvengers.repository.models.*
import com.appvengers.repository.network.NetworkEntitiesFetcher
import com.appvengers.repository.network.NetworkEntitiesFetcherImpl
import com.appvengers.repository.network.NetworkManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.ref.WeakReference

class RepositoryObjectInjector(private val weakContext: WeakReference<Context>)
{
    val dbHelper: DBHelper by lazy { DBHelper(weakContext.get()!!) }
    fun buildRepository(): Repository
    {
        return RepositoryImpl(buildCache(), buildNetworkEntitiesFetcher())
    }

    internal fun buildCache(): Cache
    {

        return CacheImpl(buildUserDaoPersistable(), buildDogDaoPersistable(),buildDogLikePersistable())
    }

    internal fun buildNetworkEntitiesFetcher(): NetworkEntitiesFetcher
    {
        return NetworkEntitiesFetcherImpl(buildNetworkManager())
    }
    internal fun buildNetworkManager(): NetworkManager
    {
        return getRetrofit().create(NetworkManager::class.java)
    }

    internal fun buildUserDaoPersistable(): DAOPersistable<UserEntityWrapper>
    {
        return UserDAO(dbHelper.getSession())
    }

    internal fun buildDogDaoPersistable(): DAOPersistable<DogEntityWrapper>
    {
        return DogDAO(dbHelper.getSession())
    }

    internal fun buildDogLikePersistable(): DAOPersistable<DogLikeEntityWrapper> {
        return DogLikesDAO(dbHelper.getSession())
    }

    private fun getHttpClient(): OkHttpClient =
            OkHttpClient.Builder()
                    .addInterceptor(HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY })
                    .addInterceptor { chain ->
                        val request = chain.request()
                        val originalHttpURL = request.url()

                        val finalURL = originalHttpURL.newBuilder()
                                .addQueryParameter("language", "es-ES")
                                .build()

                        val finalRequest = request.newBuilder()
                                .url(finalURL)
                                .build()

                        chain.proceed(finalRequest)
                    }
                    .build()
   private fun getRetrofit(): Retrofit =
            Retrofit.Builder()
                    .baseUrl(BuildConfig.TINDOGS_API_URL)
                    .client(getHttpClient())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
}