package com.appvengers.tindogs.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import com.appvengers.business.models.User
import com.appvengers.tindogs.BaseActivity
import com.appvengers.tindogs.R
import com.appvengers.tindogs.di.ObjectInjector
import com.appvengers.tindogs.router.Router
import com.appvengers.utils.LogTindogs
import com.appvengers.utils.PreferencesRepository
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.basgeekball.awesomevalidation.utility.RegexTemplate
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.lang.ref.WeakReference

class MainActivity : BaseActivity(), LoginContract.View
{

    companion object
    {
        fun intent(context: Context): Intent
        {
            val intent = Intent(context, MainActivity::class.java)
            return intent
        }
    }

    private lateinit var presenter: LoginContract.Presenter
    private lateinit var validator: AwesomeValidation


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        associatePresenter()
        checkToken()
        setUpToolbar()
        setup()

    }
    private fun checkToken()
    {
        LogTindogs("Check token", Log.DEBUG)
        val userInfo = getTokenAndUserId()
        if (userInfo != null)
        {
            LogTindogs("Token Got: " + userInfo.userId, Log.DEBUG)
            presenter.getUserById(userInfo.userId, userInfo.token)
        }
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
        presenter = LoginPresenter(this, ObjectInjector.buildGetUserInteractor(this))
    }

    override fun openRegisterForm()
    {
        Router.navigateToRegisterForm(this)
    }

    override fun openUserProfile()
    {
        Router.navigateToUserProfile(this)
    }

    override fun setLoginError(message: String) {
        setError(login_main_view, message)
    }

    override fun saveTokenAndUserId(token: String, userId: String)
    {
        val preferences = PreferencesRepository(WeakReference(this))
        preferences.saveTokenAndUserId(
                getString(R.string.sharedPerferencesFileName),
                getString(R.string.sharedPreferencesToken),
                token,
                getString(R.string.sharedPreferencesUserId),
                userId)
    }
}
