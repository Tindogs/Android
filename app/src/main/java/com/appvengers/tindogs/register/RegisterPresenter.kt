package com.appvengers.tindogs.register

import android.net.Uri
import com.appvengers.business.interactors.UploadUserPhotoInteractor
import com.appvengers.business.interactors.userCRUD.CreateUserInteractor
import com.appvengers.business.models.User
import com.appvengers.tindogs.uploads.Uploads
import com.appvengers.tindogs.uploads.UploadsFirebaseImpl

class RegisterPresenter(private val view: RegisterContract.View, private val createUserInteractor: CreateUserInteractor, private val uploadUserPhotoInteractor: UploadUserPhotoInteractor): RegisterContract.Presenter
{
    override fun registerUser(firstName: String, lastName: String, email: String, userName: String, password: String,photo: String)
    {
        createUserInteractor.execute(firstName, lastName, email, userName, password,photo,
                success = {user: User, token: String ->
                    view.saveTokenAndUserId(token, user._id)
                    view.openUserProfile()
                },
                error = { message ->
                    view.setRegisterError(message)
                })
    }

    override fun uploadUserPhoto(photo: Uri, success: (uri: Uri) -> Unit, error: (msg: String) -> Unit) {
        /*uploadUserPhotoInteractor.execute(photo,success = {
            success(it)
        }, error = {
            view.setRegisterError(it)
        })*/
        val uploadsFirebaseImpl : Uploads = UploadsFirebaseImpl()
        uploadsFirebaseImpl.uploadPhoto(uri = photo, success = {
            success(it)
        }, error = {
            error(it)
        })
    }

}