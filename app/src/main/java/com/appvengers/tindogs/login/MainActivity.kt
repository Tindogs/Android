package com.appvengers.tindogs.login

import android.os.Bundle
import com.appvengers.business.models.User
import com.appvengers.tindogs.BaseActivity
import com.appvengers.tindogs.R
import com.appvengers.tindogs.di.ObjectInjector
import com.appvengers.tindogs.router.Router
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.basgeekball.awesomevalidation.utility.RegexTemplate
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : BaseActivity(), LoginContract.View
{
    private lateinit var presenter: LoginContract.Presenter
    private lateinit var validator: AwesomeValidation

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setUpToolbar()
        associatePresenter()
        setup()

    }

    private fun setup()
    {
        addValidation()
        login_button_login.setOnClickListener {
            if (validator.validate())
            {
                presenter.getUser(login_user.text.toString(), login_password.text.toString())
            }
        }
        login_button_register.setOnClickListener { presenter.goToRegister() }
    }

    private fun addValidation()
    {
        validator = AwesomeValidation(ValidationStyle.BASIC)
        validator.addValidation(login_user, RegexTemplate.NOT_EMPTY, getString(R.string.login_validation_user_not_blank))
        validator.addValidation(login_password, RegexTemplate.NOT_EMPTY, getString(R.string.login_validation_password_not_blank))
    }

    private fun associatePresenter()
    {
        presenter = LoginPresenter(this, ObjectInjector().buildGetUserInteractor(this))
    }

    override fun openRegisterForm()
    {
        Router.navigateToRegisterForm(this)
    }

    override fun openUserProfile(user: User)
    {
        Router.navigateToUserProfile(this, user)
    }

    override fun setLoginError(message: String)
    {
        setError(login_main_view, message)
    }
}
