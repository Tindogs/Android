package com.appvengers.tindogs.dogProfile

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.appvengers.business.models.Dog
import com.appvengers.tindogs.BaseActivity
import com.appvengers.tindogs.R
import com.appvengers.utils.KeyUserInfo

class DogProfileActivity : BaseActivity(), DogProfileContract.View {

    companion object
    {
        const val ARG_USER_ID = "ARG_USER_ID"
        const val ARG_DOG_ID = "ARG_DOG_ID"
        fun intent(context: Context, userId: String, dogId: String): Intent
        {
            val intent = Intent(context, DogProfileActivity::class.java)
            intent.putExtra(ARG_USER_ID, userId)
            intent.putExtra(ARG_DOG_ID,dogId)
            return intent
        }
    }

    private lateinit var presenter: DogProfileContract.Presenter
    private lateinit var userInfo: KeyUserInfo

    private lateinit var userDogId: String
    private lateinit var dogId: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dog_profile)

        userDogId = intent.getStringExtra(ARG_USER_ID)
        dogId = intent.getStringExtra(ARG_DOG_ID)
    }

    override fun bindDogData(dog: Dog) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
