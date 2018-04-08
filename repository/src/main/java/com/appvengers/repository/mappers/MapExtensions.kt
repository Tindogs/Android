package com.appvengers.repository.mappers

import com.appvengers.db.DogEntity
import com.appvengers.db.DogLikeEntity
import com.appvengers.db.PhotosEntity
import com.appvengers.repository.models.*
import com.appvengers.repository.models.UserEntityWrapper
import com.appvengers.repository.network.model.*

fun UserEntityWrapper.map(): com.appvengers.db.UserEntity
{
    return com.appvengers.db.UserEntity(this._id, this.firstName, this.lastName, this.email, this.userName, this.coordinates?.first, this.coordinates?.second,this.photo)
}

fun com.appvengers.db.UserEntity?.map(): UserEntityWrapper?
{
    if (this != null)
    {
        return UserEntityWrapper(this._id, this.firstName, this.lastName, this.email, this.userName, Pair(this.latitude, this.longitude),this.dogs.map { it.map() }, this.photo)
    }
    else
    {
        return null
    }
}

fun UserJsonEntity.map(): UserEntityWrapper
{
    val firstName = this.firstName ?: ""
    val lastName = this.lastName ?: ""
    val email = this.email ?: ""
    val dogs = this.dogs ?: listOf()
    val photo = this.photo
    val coordinates = if (this.coordinates != null && this.coordinates.count() > 0)
    {
        Pair(this.coordinates[0], this.coordinates[1])
    }
    else
    {
        null
    }
    return UserEntityWrapper(this._id, firstName, lastName, email, this.userName, coordinates, dogs.map { it.map(this._id) },photo)
}

fun UserJsonEntity.mapDogs(): List<DogEntityWrapper>
{
    val dogs = this.dogs ?: listOf()
    return dogs.map { it.map(this._id) }
}
fun ResultUserJson.map(): UserEntityWrapper
{
    return this.result.map()
}

fun ResultUserJson.mapDogs(): List<DogEntityWrapper>
{
    return this.result.mapDogs()
}

fun DogEntityWrapper.map(): com.appvengers.db.DogEntity
{
    val dog = DogEntity(this._id, this.userId, this.name, this.age, this.breed, this.pureBreed, this.color, this.description)
    dog.query = this.query.map(dog._id)
    return dog
}

fun DogJsonEntity.map(userId: String): DogEntityWrapper
{
    val query = this.query ?: QueryJsonEntity(1.0, 1.0, false, "")
    val likesFromOthers = this.likesFromOthers ?: listOf()
    val breed = this.breed ?: "Desconocido"
    val color = this.color ?: ""
    val description = this.description ?: ""
    val photos = this.photos ?: listOf()
    val pureBreed = this.pureBreed ?: false
    val age = this.age ?: 1.0
    return DogEntityWrapper(this._id, this.name, age, breed, pureBreed, color, description, photos, query.map(), likesFromOthers.map { it.map() }, userId)
}

fun com.appvengers.db.DogEntity.map():DogEntityWrapper
{

    return DogEntityWrapper(this._id, this.name, this.age, this.breed, this.pureBreed, this.color, this.description, this.photos.map { it.map() }, this.query.map(), this.likesFromOthers.map { it.map() }, this.userId)
}

fun DogLikeEntityWrapper.map(dogId: String): com.appvengers.db.DogLikeEntity
{
    val dogLikeEntity= com.appvengers.db.DogLikeEntity()
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
    return DogLikeEntityWrapper(this.dogIdWhoLikes, this.name)
}

fun QueryEntityWrapper.map(dogId: String): com.appvengers.db.QueryEntity
{
    return com.appvengers.db.QueryEntity(dogId, this.age, this.maxKms, this.reproductive, this.breed)
}

fun QueryJsonEntity.map(): QueryEntityWrapper
{
    return QueryEntityWrapper(this.age, this.maxKms, this.reproductive, this.breed)
}
fun com.appvengers.db.QueryEntity.map(): QueryEntityWrapper
{
    return QueryEntityWrapper(this.age, this.maxKms, this.reproductive, this.breed)
}

fun String.mapToPhotoEntity(dogId: String): com.appvengers.db.PhotosEntity
{
    val photosEntity = PhotosEntity()
    photosEntity.dogId = dogId
    photosEntity.photo = this
    return photosEntity
}

fun com.appvengers.db.PhotosEntity.map(): String
{
    return this.photo
}