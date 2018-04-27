package com.appvengers.repository.network

import com.appvengers.repository.models.QueryEntityWrapper
import com.appvengers.repository.network.model.*
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
                   @Field("password") password: String,
                   @Field("photo") photo: String) : Flowable<ResultUserJson>

    @FormUrlEncoded
    @PUT("users/{user_id}")
    fun updateUser(@Path("user_id") userId: String,
                   @Header("token") token: String,
                   @Field("first_name") firstName: String,
                   @Field("last_name") lastName: String,
                   @Field("email") email: String,
                   @Field("username") userName: String,
                    @Field("coordinates") coordinates: List<Double>?) : Flowable<ResultUserJson>

    @FormUrlEncoded
    @PUT("users/{user_id}/dogs")
    fun createDog(@Path("user_id") userId: String,
                  @Header("token") token: String,
                  @Field("name") name: String,
                  @Field("age") age: Double,
                  @Field("breed") breed: String,
                  @Field("purebreed") pureBreed: Boolean,
                  @Field("color") color: String,
                  @Field("description") description: String,
                  @Field("photos") photos: List<String>,
                  @Field("queryage") queryAge: Double,
                  @Field("querymaxkms") queryKms: Double,
                  @Field("queryreproductive") queryReproductive: Boolean,
                  @Field("querybreed") queryBreed: String): Flowable<ResultUserJson>

    @GET("users/{user_id}/dogs/{dog_id}/search")
    fun getDogsList(@Path("user_id") userId: String,
                    @Path("dog_id") dogId: String,
                    @Header("token") token: String): Flowable<ResultDogsJson>

    @FormUrlEncoded
    @PUT("users/{user_id}/dogs/{dog_id}/like/{dog_who_like_id}")
    fun putNewDogLike( @Path("user_id") userId: String,
                       @Path("dog_id") dogId: String,
                       @Path("dog_who_like_id") dogWhoLikeId: String,
                       @Field("like")  like: Boolean,
                       @Header("token") token: String) : Flowable<ResultLikesJson>

    @GET("users/{user_id}/dogs/{dog_id}")
    fun getDogDetail(@Path("user_id") userId: String,
                     @Path("dog_id") dogId: String,
                     @Header("token") token: String) : Flowable<ResultDogDetailJson>
}