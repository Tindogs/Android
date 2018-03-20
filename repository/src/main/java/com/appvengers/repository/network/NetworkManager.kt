package com.appvengers.repository.network

import com.appvengers.repository.network.model.ResultUserJson
import com.appvengers.repository.network.model.UserJsonEntity
import io.reactivex.Flowable
import retrofit2.http.*

internal interface NetworkManager
{
    @FormUrlEncoded
    @POST("users/authenticate")
   fun getUser(@Field("email") email: String, @Field("password") password: String): Flowable<ResultUserJson>


    @GET("users/{user_id}")
    fun getUserById(@Path("user_id") userId: String, @Header("token") token: String): Flowable<ResultUserJson>

    @FormUrlEncoded
    @POST("users/register")
    fun createUser(@Field("first_name") firstName: String,
                   @Field("last_name") lastName: String,
                   @Field("email") email: String,
                   @Field("username") userName: String,
                   @Field("password") password: String) : Flowable<ResultUserJson>
}