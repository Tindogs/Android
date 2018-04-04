package com.appvengers.tindogs.userProfile

import android.util.Log
import com.appvengers.business.interactors.userCRUD.GetUserInteractor
import com.appvengers.business.interactors.userCRUD.UpdateUserInteractor
import com.appvengers.business.models.Dog
import com.appvengers.business.models.User
import com.appvengers.utils.LogTindogs

class UserProfilePresenter(private  val view: UserProfileContract.View,
                           private val getUserInteractor: GetUserInteractor,
                           private val updateUserInteractor: UpdateUserInteractor
                           ): UserProfileContract.Presenter
{

    private lateinit var user: User

    override fun updateCoordinates(latitude: Double, longitude: Double, token: String)
    {
        user.coordinates = Pair(latitude, longitude)
        LogTindogs("User updating:" + user.toString(), Log.DEBUG)
        updateUserInteractor.execute(user, token,
                success =
                {
                    LogTindogs("User coordinates updated. User: " + user.toString(), Log.INFO)
                },
                error =
                {
                    LogTindogs("User coordinates NOT updated. Message: " + it + "| User: " + user.toString() , Log.ERROR)
                })
    }
    override fun getUser(userId: String, token: String)
    {
        getUserInteractor.execute(userId, token,
                success = { user: User ->
                    LogTindogs("User got (userId): ", Log.DEBUG)
                    this.user = user
                    view.renderUser(user.userName)
                    view.getLocation()
                    view.setupDogList(user.dogs)
                },
                error = {
                    LogTindogs(it, Log.ERROR)
                    view.onUserNotFound()
                })
    }


}

