package com.appvengers.tindogs.uploads

import android.net.Uri
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import com.google.firebase.storage.UploadTask

class UploadsFirebaseImpl : Uploads {

    val mStorage : StorageReference = FirebaseStorage.getInstance().reference

    override fun uploadPhoto(uri: Uri, success: (downloadUrl: Uri) -> Unit, error: (msg: String) -> Unit) {
        var mReference = mStorage.child(uri.lastPathSegment)
        try {
            mReference.putFile(uri).addOnSuccessListener {
                taskSnapshot: UploadTask.TaskSnapshot? ->
                val url = taskSnapshot!!.downloadUrl
                url?.let {
                    success(it)
                }

            }.addOnFailureListener {
                        error(it.message!!)
                    }
        }catch (e: Exception) {
            error(e.message!!)
        }
    }
}