package com.appvengers.repository.mappers

import com.appvengers.repository.models.*
import com.appvengers.repository.network.model.DogJsonEntity
import com.appvengers.repository.network.model.DogLikeJsonEntity
import com.appvengers.repository.network.model.QueryJsonEntity
import com.appvengers.repository.network.model.UserJsonEntity

fun UserEntityWrapper.map(): UserEntity
{
    return UserEntity(this._id, this.firstName, this.lastName, this.phone, this.mobilePhone, this.email, this.userName, this.coordinates.first, this.coordinates.second)
}

fun UserEntity?.map(): UserEntityWrapper?
{
    if (this != null)
    {
        return UserEntityWrapper(this._id, this.firstName, this.lastName, this.phone, this.mobilePhone, this.email, this.userName, Pair(this.latitude, this.longitude),this.dogs.map { it.map() })
    }
    else
    {
        return null
    }
}

fun UserJsonEntity.map(): UserEntityWrapper
{
    return UserEntityWrapper(this._id, this.firstName, this.lastName, this.phone, this.mobilePhone, this.email, this.userName, this.coordinates, this.dogs.map { it.map(this._id) })
}

fun DogEntityWrapper.map(userId: Long): DogEntity
{
    val dog =  DogEntity(this._id, userId,this.name, this.age, this.breed, this.pureBreed, this.color, this.description)
    dog.query = this.query.map(dog._id)
    return dog
}

fun DogJsonEntity.map(userId: Long): DogEntityWrapper
{
    return DogEntityWrapper(this._id, this.name, this.age, this.breed, this.pureBreed, this.color, this.description, this.photos, this.query.map(), this.likesFromOthers.map { it.map() }, userId)
}

fun DogEntity.map():DogEntityWrapper
{
    return DogEntityWrapper(this._id, this.name, this.age, this.breed, this.pureBreed, this.color, this.description, this.photos.map { it.map() }, this.query.map(), this.likesFromOthers.map { it.map() }, this.userId)
}

fun DogLikeEntityWrapper.map(dogId: Long): DogLikeEntity
{
    val dogLikeEntity= DogLikeEntity()
    dogLikeEntity.dogIdLiked = dogId
    dogLikeEntity.dogIdWhoLikes = this.dogWhoLikes
    dogLikeEntity.name = this.name
    return dogLikeEntity
}

fun DogLikeJsonEntity.map(): DogLikeEntityWrapper
{
    return DogLikeEntityWrapper(this.dogWhoLikes, this.name)
}

fun DogLikeEntity.map(): DogLikeEntityWrapper
{
    return DogLikeEntityWrapper(this._id, this.name)
}

fun QueryEntityWrapper.map(dogId: Long): QueryEntity
{
    return QueryEntity(dogId,this.ageFrom, this.ageTo, this.maxKms, this.reproductive, this.breed)
}

fun QueryJsonEntity.map(): QueryEntityWrapper
{
    return QueryEntityWrapper(this.ageFrom, this.ageTo, this.maxKms, this.reproductive, this.breed)
}
fun QueryEntity.map(): QueryEntityWrapper
{
    return QueryEntityWrapper(this.ageFrom, this.ageTo, this.maxKms, this.reproductive, this.breed)
}

fun String.mapToPhotoEntity(dogId: Long): PhotosEntity
{
    val photosEntity = PhotosEntity()
    photosEntity.dogId = dogId
    photosEntity.photo = this
    return photosEntity
}

fun  PhotosEntity.map(): String
{
    return this.photo
}