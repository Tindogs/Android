package com.appvengers.business.interactors

import android.net.Uri

/**
 * Created by carlosledesma on 5/4/18.
 */
interface UploadUserPhotoInteractor {
    fun execute(uri: Uri, success: (photo: Uri) -> Unit, error: (msg: String) -> Unit )
}