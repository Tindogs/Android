package com.appvengers.business.interactors

import android.net.Uri
/*import com.appvengers.repository.network.Uploads
import com.appvengers.repository.network.UploadsFirebaseImpl*/

class UploadUserPhotoInteractorImpl : UploadUserPhotoInteractor {
    override fun execute(uri: Uri, success: (photo: Uri) -> Unit, error: (msg: String) -> Unit) {
        /*val uploadsFirebaseImpl : Uploads = UploadsFirebaseImpl()
        uploadsFirebaseImpl.uploadPhoto(uri,success = {
            success(it)
        }, error = {
           error(it)
        })*/
    }
}