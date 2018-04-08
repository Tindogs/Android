package com.appvengers.tindogs.dogRegister

import android.net.Uri


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

        fun uploadDogPhoto(photo: Uri,success: (uri: Uri) -> Unit, error: (msg: String) -> Unit)
    }
}