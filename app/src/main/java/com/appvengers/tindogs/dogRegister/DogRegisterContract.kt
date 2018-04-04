package com.appvengers.tindogs.dogRegister


interface DogRegisterContract
{
    interface View
    {
        fun openUserProfile()
        fun setRegisterDogError(message: String)
    }
    interface Presenter
    {
       fun registerDog(
                userId: String,
                token: String,
                name: String,
                age: Double,
                breed: String,
                pureBreed: Boolean,
                color: String,
                description: String,
                photos: List<String>
        )
    }
}