package com.appvengers.tindogs.editUserProfile

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.SpannableStringBuilder
import com.appvengers.business.models.User
import com.appvengers.tindogs.BaseActivity
import com.appvengers.tindogs.R
import com.appvengers.tindogs.di.ObjectInjector
import com.basgeekball.awesomevalidation.AwesomeValidation
import com.basgeekball.awesomevalidation.ValidationStyle
import com.basgeekball.awesomevalidation.utility.RegexTemplate
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_edit_user_profile.*

class EditUserProfileActivity : BaseActivity(), EditUserProfileContract.View {

    companion object
    {
        const val REQUEST_NUMBER = 123
        private val REQUEST_CODE_USER_PHOTO = 1234
        fun intent(context: Context): Intent
        {
            val intent = Intent(context, EditUserProfileActivity::class.java)
            return intent
        }
    }

    private lateinit var validator: AwesomeValidation
    private lateinit var presenter: EditUserProfileContract.Presenter

    private lateinit var userId: String
    private lateinit var token: String
    private var photoUrl : String = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle("Edición de perfil")
        associatePresenter()
        addValidation()
        setContentView(R.layout.activity_edit_user_profile)

        edit_profile_button_cancel.setOnClickListener {
            commonOnCancel()
        }

        token = getTokenAndUserId()!!.token
        userId = getTokenAndUserId()!!.userId

        edit_profile_button_save.setOnClickListener {
            if(validator.validate()) {
                val firstName = edit_name_user_profile.text.toString()
                val lastName = edit_surename_user_profile.text.toString()
                val userName = edit_username_user_profile.text.toString()
                val uri = Uri.parse(photoUrl)
                presenter.updateUser(firstName,lastName,userName, uri,token)
            }
        }

        edit_user_photo_profile.setOnClickListener {
            var intentPicker = Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intentPicker,REQUEST_CODE_USER_PHOTO)
        }

        presenter.getUser(userId, token)
    }

    override fun onBackPressed() {
        commonOnCancel()
    }

    private fun commonOnCancel() {
        val intent = Intent()
        setResult(Activity.RESULT_CANCELED,intent)
        finish()
    }

    private fun commonOnOk() {
        val intent = Intent()
        setResult(Activity.RESULT_OK, intent)
        finish()
    }

    private fun addValidation()
    {
        validator = AwesomeValidation(ValidationStyle.BASIC)
        validator.addValidation(edit_name_user_profile, RegexTemplate.NOT_EMPTY, getString(R.string.register_validation_first_name))
        validator.addValidation(edit_surename_user_profile, RegexTemplate.NOT_EMPTY, getString(R.string.register_validation_last_name))
        validator.addValidation(edit_username_user_profile, RegexTemplate.NOT_EMPTY, getString(R.string.register_validation_last_name))


    }

    private fun associatePresenter()
    {
        presenter = EditUserProfilePresenter(this,
                ObjectInjector.buildUpdateUserInteractor(this),
                ObjectInjector.buildGetUserInteractor(this))
    }

    override fun bindUserData(user: User) {
        edit_name_user_profile.text = SpannableStringBuilder(user.firstName)
        edit_surename_user_profile.text = SpannableStringBuilder(user.lastName)
        edit_username_user_profile.text = SpannableStringBuilder(user.userName)
        if(user.photo != "") {
            photoUrl = user.photo
            loadWithPicasso(Uri.parse(user.photo))
        } else {
            Picasso.with(this)
                    .load(R.drawable.dog_placeholder)
                    .into(edit_user_photo_profile)
        }
    }

    override fun setErrorView(msg: String) {
       setError(edit_user_profile_main_view, msg)
    }

    override fun setResultOk() {
        commonOnOk()
    }

    override fun setResultCancel() {
        commonOnCancel()
    }

    override fun setNewPhotoUrl(uri: Uri) {
        loadWithPicasso(uri)
    }

    private fun loadWithPicasso(uri: Uri) {
        Picasso.with(this)
                .load(uri)
                .placeholder(R.drawable.dog_placeholder)
                .into(edit_user_photo_profile)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when(requestCode) {
            REQUEST_CODE_USER_PHOTO ->
                    when(resultCode) {
                        Activity.RESULT_OK ->
                                data?.let {
                                    var resp : Uri = data.data
                                    presenter.uploadPhoto(resp)
                                }
                    }
        }
    }
}
