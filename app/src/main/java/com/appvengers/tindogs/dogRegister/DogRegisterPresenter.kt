package com.appvengers.tindogs.dogRegister

import android.net.Uri
import com.appvengers.business.interactors.dogCRUD.CreateDogInteractor
import com.appvengers.tindogs.uploads.Uploads
import com.appvengers.tindogs.uploads.UploadsFirebaseImpl


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

    override fun uploadDogPhoto(photo: Uri, success: (uri: Uri) -> Unit, error: (msg: String) -> Unit) {
        val uploads : Uploads = UploadsFirebaseImpl()
        uploads.uploadPhoto(uri = photo, success = {
            success(it)
        }, error = {
            error(it)
        })
    }

}