package com.appvengers.business.mappers

import com.appvengers.business.models.Dog
import com.appvengers.business.models.DogLike
import com.appvengers.business.models.Query
import com.appvengers.business.models.User
import com.appvengers.repository.models.*

internal fun UserEntityWrapper.map(): User
{
    return User(this._id, this.firstName, this.lastName, this.phone, this.mobilePhone, this.email, this.userName, this.coordinates, this.dogs.map { it.map() })
}

internal fun DogEntityWrapper.map(): Dog
{
    return Dog(this ._id, this.name, this.age, this.breed, this.pureBreed, this.color, this.description, this.photos, this.query.map(), this.likesFromOthers.map { it.map() })
}

internal fun QueryEntityWrapper.map(): Query
{
    return Query(this.ageFrom, this.ageTo, this.maxKms, this.reproductive, this.breed)
}

internal fun DogLikeEntityWrapper.map(): DogLike
{
    return DogLike(this.dogWhoLikes, this.name)
}