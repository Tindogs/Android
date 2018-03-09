package com.appvengers.business.mappers

import com.appvengers.business.models.Dog
import com.appvengers.business.models.DogLike
import com.appvengers.business.models.Query
import com.appvengers.business.models.User
import com.appvengers.repository.models.DogEntity
import com.appvengers.repository.models.DogLikeEntity
import com.appvengers.repository.models.QueryEntity
import com.appvengers.repository.models.UserEntity

internal fun UserEntity.map(): User
{
    return User(this._id, this.firstName, this.lastName, this.phone, this.mobilePhone, this.email, this.userName, Pair(this.latitude, this.longitude), this.dogs.map { it.map() })
}

internal fun DogEntity.map(): Dog
{
    return Dog(this ._id, this.name, this.age, this.breed, this.pureBreed, this.color, this.description, this.photos.map { it.photo }, this.query.map(), this.likesFromOthers.map { it.map() })
}

internal fun QueryEntity.map(): Query
{
    return Query(this.ageFrom, this.ageTo, this.maxKms, this.reproductive, this.breed)
}

internal fun DogLikeEntity.map(): DogLike
{
    return DogLike(this._id, this.name)
}