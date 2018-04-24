package com.appvengers.business.mappers

import com.appvengers.business.models.Dog
import com.appvengers.business.models.DogLike
import com.appvengers.business.models.Query
import com.appvengers.business.models.User
import com.appvengers.repository.models.*

internal fun UserEntityWrapper.map(): User
{
    return User(this._id, this.firstName, this.lastName, this.email, this.userName, this.coordinates, this.dogs.map { it.mapToDog() }, this.photo)
}

internal fun User.map(): UserEntityWrapper
{
    return UserEntityWrapper(this._id, this.firstName, this.lastName, this.email, this.userName, this.coordinates, this.dogs.map { it.map(this._id) }, this.photo)
}

internal fun DogEntityWrapper.mapToDog(): Dog
{
    return Dog(this ._id, this.name, this.age, this.breed, this.pureBreed, this.color, this.description, this.photos, this.query.map(), this.likesFromOthers.map { it.map(this._id) })
}

internal fun Dog.map(userId: String): DogEntityWrapper
{
    return DogEntityWrapper(this._id, this.name, this.age, this.breed, this.pureBreed, this.color, this.description, this.photos,this.query.map(), this.likesFromOthers.map { it.map(this._id) }, userId)
}

internal fun QueryEntityWrapper.map(): Query
{
    return Query(this.age, this.maxKms, this.reproductive, this.breed)
}

internal fun Query.map(): QueryEntityWrapper
{
    return QueryEntityWrapper(this.age, this.maxKms, this.reproductive,this.breed)
}

internal fun DogLikeEntityWrapper.map(dogId: String): DogLike
{
    val dogName = this.dogName ?: ""
    return DogLike(this.dogWhoLikesId, dogName, dogId)
}

internal fun DogLike.map(dogId: String): DogLikeEntityWrapper
{
    return DogLikeEntityWrapper(this.dogWhoLikesId, this.name,dogId)
}