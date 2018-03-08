package com.appvengers.tindogs.register

import android.content.Context
import android.content.Intent

import android.os.Bundle
import android.util.Patterns
import com.appvengers.business.models.User
import com.appvengers.tindogs.BaseActivity
import com.appvengers.tindogs.R
import com.appvengers.tindogs.di.ObjectInjector
import com.appvengers.tindogs.router.Router

import kotlinx.android.synthetic.main.activity_register.*
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.basgeekball.awesomevalidation.utility.RegexTemplate

// TODO("Coordenadas")
class RegisterActivity : BaseActivity(), RegisterContract.View
{

    companion object
    {
       fun intent(context: Context): Intent
        {
            val intent = Intent(context, RegisterActivity::class.java)
            return intent
        }
    }

    private lateinit var presenter: RegisterContract.Presenter
    private lateinit var validator: AwesomeValidation

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        setUpToolbar(homeIsEnabled = true)
        associatePresenter()
        setup()
    }

    private fun setup()
    {
        addValidation()
        register_button_save.setOnClickListener {

            if(validator.validate())
            {
                presenter.registerUser(
                        register_first_name.text.toString(),
                        register_last_name.text.toString(),
                        register_phone.text.toString(),
                        register_mobile_phone.text.toString(),
                        register_email.text.toString(),
                        register_user_name.text.toString(),
                        register_password.text.toString()
                )
            }
        }
    }

    private fun addValidation()
    {
        validator = AwesomeValidation(ValidationStyle.BASIC)
        validator.addValidation(register_first_name, RegexTemplate.NOT_EMPTY, getString(R.string.register_validation_first_name))
        validator.addValidation(register_last_name, RegexTemplate.NOT_EMPTY, getString(R.string.register_validation_last_name))
        validator.addValidation(register_email, Patterns.EMAIL_ADDRESS, getString(R.string.register_validation_email))
        validator.addValidation(register_email, register_email_confirm, getString(R.string.register_validation_email_confirm))
        val paswordRegex = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])([^\\s]){8,16}\$"
        validator.addValidation(register_password, paswordRegex, getString(R.string.register_validation_password))
        validator.addValidation(register_password, register_password_confirm, getString(R.string.register_validation_password_confirm))
    }

    private fun associatePresenter()
    {
        presenter = RegisterPresenter(this, ObjectInjector().buildCreateUserInteractor(this))
    }

    override fun openUserProfile(user: User)
    {
        Router.navigateToUserProfile(this, user)
    }

    override fun setRegisterError(message: String)
    {
        setError(register_main_view, message)
    }
}