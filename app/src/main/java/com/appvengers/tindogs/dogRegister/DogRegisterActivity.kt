package com.appvengers.tindogs.dogRegister

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.appvengers.tindogs.BaseActivity
import com.appvengers.tindogs.R
import com.appvengers.tindogs.di.ObjectInjector
import com.appvengers.tindogs.router.Router
import kotlinx.android.synthetic.main.activity_dog_register.*
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.appvengers.utils.KeyUserInfo
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.basgeekball.awesomevalidation.utility.RegexTemplate
import com.google.common.collect.Range


class DogRegisterActivity : BaseActivity(), DogRegisterContract.View
{


    companion object
    {
        fun intent(context: Context): Intent
        {
            val intent = Intent(context, DogRegisterActivity::class.java)
            return intent
        }
    }

    private lateinit var presenter: DogRegisterContract.Presenter
    private lateinit var userInfo: KeyUserInfo
    private lateinit var validator: AwesomeValidation

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dog_register)

        associatePresenter()
        setup()
    }

    private fun associatePresenter()
    {
        presenter = DogRegisterPresenter(this, ObjectInjector.buildCreateDogInteractor(this))
    }

    private fun setup()
    {
        val userInfo = getTokenAndUserId()
        if (userInfo == null)
        {
            deleteUserInfo()
            Router.navigateToRegisterForm(this)
        }
        else
        {
            this.userInfo = userInfo
            addValidation()
            setupRegisterButton()
        }

    }

    private fun addValidation()
    {
        validator = AwesomeValidation(ValidationStyle.BASIC)
        validator.addValidation(dog_register_dog_name, RegexTemplate.NOT_EMPTY, getString(R.string.dog_register_validation_name_empty))
        validator.addValidation(dog_register_dog_age, Range.closed(0, 30), getString(R.string.dog_register_validation_age))
        validator.addValidation(dog_register_dog_description, RegexTemplate.NOT_EMPTY, getString(R.string.dog_register_validation_desription_empty))
    }

    private fun setupRegisterButton()
    {
        dog_register_register_button.setOnClickListener {
            if (validator.validate())
            {
                presenter.registerDog(
                        userInfo.userId,
                        userInfo.token,
                        dog_register_dog_name.text.toString(),
                        dog_register_dog_age.text.toString().toDouble(),
                        dog_register_dog_breed.selectedItem.toString(),
                        dog_register_dog_pure_breed.isActivated,
                        dog_register_dog_color.text.toString(),
                        dog_register_dog_description.text.toString(),
                        listOf())
            }
        }
    }


    override fun openUserProfile()
    {
        Router.navigateToUserProfile(this)
    }

    override fun setRegisterDogError(message: String)
    {
        setError(dog_register_main_view, message)
    }
}
