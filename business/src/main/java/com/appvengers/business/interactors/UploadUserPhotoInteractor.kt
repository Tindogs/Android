package com.appvengers.business.interactors

import android.net.Uri

interface UploadUserPhotoInteractor {
    fun execute(uri: Uri, success: (photo: Uri) -> Unit, error: (msg: String) -> Unit )
}