package com.appvengers.tindogs.register

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri

import android.os.Bundle
import android.util.Patterns
import com.appvengers.tindogs.BaseActivity
import com.appvengers.tindogs.R
import com.appvengers.tindogs.di.ObjectInjector
import com.appvengers.tindogs.router.Router
import com.appvengers.utils.PreferencesRepository

import kotlinx.android.synthetic.main.activity_register.*
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.basgeekball.awesomevalidation.utility.RegexTemplate
import com.squareup.picasso.Picasso
import java.lang.ref.WeakReference

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

    /* User photo */
    private var photoUrl: String = ""
    private val REQUEST_CODE_USER_PHOTO = 0


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        associatePresenter()
        setContentView(R.layout.activity_register)
        setUpToolbar(homeIsEnabled = true)
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
                        register_email.text.toString(),
                        register_user_name.text.toString(),
                        register_password.text.toString(),
                        this.photoUrl
                )
            }
        }
        register_user_photo.setOnClickListener {

            var intentPicker = Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intentPicker,REQUEST_CODE_USER_PHOTO)

        }
    }

    private fun addValidation()
    {
        validator = AwesomeValidation(ValidationStyle.BASIC)
        validator.addValidation(register_first_name, RegexTemplate.NOT_EMPTY, getString(R.string.register_validation_first_name))
        validator.addValidation(register_last_name, RegexTemplate.NOT_EMPTY, getString(R.string.register_validation_last_name))
        validator.addValidation(register_email, Patterns.EMAIL_ADDRESS, getString(R.string.register_validation_email))
        validator.addValidation(register_email, register_email_confirm, getString(R.string.register_validation_email_confirm))
        val passwordRegex = "^(?=.*\\d)(?=.*[A-Z])(?=.*[a-z])([^\\s]){8,16}\$"
        validator.addValidation(register_password, passwordRegex, getString(R.string.register_validation_password))
        validator.addValidation(register_password, register_password_confirm, getString(R.string.register_validation_password_confirm))
    }

    private fun associatePresenter()
    {
        presenter = RegisterPresenter(this, ObjectInjector.buildCreateUserInteractor(this),ObjectInjector.buildUploadUserPhoto(this))
    }

    override fun openUserProfile()
    {
        Router.navigateToUserProfile(this)
    }

    override fun setRegisterError(message: String)
    {
        setError(register_main_view, message)
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if(requestCode == REQUEST_CODE_USER_PHOTO
            && resultCode == Activity.RESULT_OK
            && data?.data != null) {

            var resp : Uri? = data.data
            presenter.uploadUserPhoto(resp!!, success = {
                this.photoUrl = it.toString()
                Picasso.with(this.baseContext).load(it.toString()).into(register_user_photo)
            }, error = {
                this.setRegisterError(it)
            })
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}
