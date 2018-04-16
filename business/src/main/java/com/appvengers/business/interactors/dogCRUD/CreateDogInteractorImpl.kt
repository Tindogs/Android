package com.appvengers.business.interactors.dogCRUD

import android.util.Log
import com.appvengers.business.mappers.map
import com.appvengers.business.models.Dog
import com.appvengers.business.models.Query
import com.appvengers.repository.Repository
import com.appvengers.repository.models.DogEntityWrapper
import com.appvengers.utils.LogTindogs

class CreateDogInteractorImpl(private val repository: Repository): CreateDogInteractor
{
    override fun execute(
            userId: String,
            token: String,
            name: String,
            age: Double,
            breed: String,
            pureBreed: Boolean,
            color: String,
            description: String,
            photos: List<String>,
            success: (dog: List<Dog>) -> Unit,
            error: (message: String) -> Unit
    )
    {
        LogTindogs("CreateDogInteractor: $userId, $name, $age, $breed, $pureBreed, $color, $description", Log.INFO)

        repository.createDog(userId, token, name, age, breed, pureBreed, color, description,photos,age,100000.0,true,breed,
                success = { dogList: List<DogEntityWrapper> ->
                    success(dogList.map{ it.map()})
                },
                error = {message: String ->
                    error(message)
                })
    }


}