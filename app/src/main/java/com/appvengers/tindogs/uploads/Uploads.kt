package com.appvengers.tindogs.uploads

import android.net.Uri

/**
 * Created by carlosledesma on 4/4/18.
 */
interface Uploads {

    fun uploadPhoto(uri:Uri,success: (downloadUrl: Uri) -> Unit, error: (msg: String) -> Unit)
}