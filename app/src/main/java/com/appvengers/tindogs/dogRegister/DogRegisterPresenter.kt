package com.appvengers.tindogs.dogRegister

import com.appvengers.business.interactors.dogCRUD.CreateDogInteractor


class DogRegisterPresenter(private val view: DogRegisterContract.View,
                           private val createDogInteractor: CreateDogInteractor): DogRegisterContract.Presenter
{
    override fun registerDog(userId: String, token: String, name: String, age: Double, breed: String, pureBreed: Boolean, color: String, description: String, photos: List<String>)
    {
       createDogInteractor.execute(userId, token, name, age, breed, pureBreed, color, description, photos,
               success = {
                   view.openUserProfile()
               },
               error = {
                   view.setRegisterDogError(it)
               })
    }

}