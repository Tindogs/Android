package com.appvengers.tindogs.editUserProfile

import android.net.Uri
import com.appvengers.business.interactors.userCRUD.GetUserInteractor
import com.appvengers.business.interactors.userCRUD.UpdateUserInteractor
import com.appvengers.business.models.User

class EditUserProfilePresenter(private val view: EditUserProfileContract.View,
                               private val updateUserInteractor: UpdateUserInteractor,
                               private val getUserInteractor: GetUserInteractor): EditUserProfileContract.Presenter {

    private lateinit var user: User

    override fun getUser(userId: String, token: String) {
        getUserInteractor.execute(userId = userId, token = token, success = {
            user = it
            view.bindUserData(it)
        }, error = {
            view.setErrorView(it)
        })
    }

    override fun updateUser(firstName: String, lastName: String, userName: String, photoUrl: Uri, token: String) {
        user.firstName = firstName
        user.lastName = lastName
        user.userName = userName
        user.photo = photoUrl.toString()
        updateUserInteractor.execute(user,token, success = {
            view.setResultOk()
        }, error = {
            view.setErrorView(it)
            //view.setResultCancel()
        })
    }

}