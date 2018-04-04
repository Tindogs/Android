package com.appvengers.business.interactors.dogCRUD

import com.appvengers.business.models.Dog

interface CreateDogInteractor
{
    fun execute(
            userId: String,
            token: String,
            name: String,
            age: Double,
            breed: String,
            pureBreed: Boolean,
            color: String,
            description: String,
            photos: List<String>,
            success: (dogs: List<Dog>) -> Unit,
            error: (message: String) -> Unit
    )
}