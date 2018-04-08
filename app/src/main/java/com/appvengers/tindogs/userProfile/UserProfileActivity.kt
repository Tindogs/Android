package com.appvengers.tindogs.userProfile

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.location.Location

import android.os.Bundle
import android.provider.Settings
import android.support.v4.app.ActivityCompat
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.GridLayoutManager
import android.util.Log
import com.appvengers.tindogs.R
import android.widget.Toast
import com.appvengers.business.models.Dog

import com.appvengers.tindogs.BaseActivity
import com.appvengers.tindogs.di.ObjectInjector
import com.appvengers.tindogs.router.Router
import com.appvengers.utils.LogTindogs
import com.appvengers.utils.PreferencesRepository
import com.google.android.gms.common.api.ApiException

import kotlinx.android.synthetic.main.activity_user_profile.*
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.tasks.RuntimeExecutionException
import com.squareup.picasso.Picasso


class UserProfileActivity : BaseActivity(), UserProfileContract.View
{


    companion object
    {
        const val REQUEST_CHECK_SETTINGS = 232
        fun intent(context: Context): Intent
        {
            val intent = Intent(context, UserProfileActivity::class.java)

            return intent
        }
    }


    private lateinit var presenter: UserProfileContract.Presenter
    private lateinit var token: String
    private val PERMISSION_ACCESS_COARSE_LOCATION: Int = 10


    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_profile)


        associatePresenter()
        setup()
    }

    private fun setup()
    {
        val userInfo = getTokenAndUserId()
        if (userInfo == null)
        {
            Router.navigateToRegisterForm(this)
        }
        else
        {
            token = userInfo.token
            presenter.getUser(userInfo.userId, userInfo.token)
            user_profile_add_dog_button.setOnClickListener {
                Router.navigateToDogRegister(this)
            }

        }


    }

    override fun setupDogList(dogs: List<Dog>)
    {
        user_profile_dogs_list.layoutManager = GridLayoutManager(this, 2)
        user_profile_dogs_list.itemAnimator = DefaultItemAnimator()
        val adapter =  UserDogsAdapter(dogs)
        adapter.listener = object : UserDogsAdapter.OnDogClickListener
        {
            override fun onDogSelected(dog: Dog)
            {
                LogTindogs("Dog selected $dog", Log.INFO)
            }

        }
        user_profile_dogs_list.adapter = adapter
    }

    private fun associatePresenter()
    {
        presenter = UserProfilePresenter(this, ObjectInjector.buildGetUserInteractor(this), ObjectInjector.buildUpdateUserInteractor(this))
    }

    override fun onUserNotFound()
    {
        deleteUserInfo()
        Router.navigateToLogin(this)
    }

    override fun getLocation()
    {

       if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                == PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED)
        {
            displayLocationSettingsRequest(this)
        }
        else
        {
            ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.ACCESS_COARSE_LOCATION),
                    PERMISSION_ACCESS_COARSE_LOCATION
            )
        }
    }

    @SuppressLint("MissingPermission")
    private fun displayLocationSettingsRequest(context: Context)
    {
        val googleApiClient = GoogleApiClient.Builder(context)
                .addApi(LocationServices.API).build()
        googleApiClient.connect()

        val locationRequest = LocationRequest.create()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 10000
        locationRequest.fastestInterval = 10000 / 2

        val builder = LocationSettingsRequest.Builder().addLocationRequest(locationRequest)
        builder.setAlwaysShow(true)

        val result = LocationServices.getSettingsClient(context).checkLocationSettings(builder.build())
        result.addOnCompleteListener {
           try
           {
               it.result
               val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
               fusedLocationClient.lastLocation
                       .addOnSuccessListener { location: Location? ->
                           LogTindogs("Location" + location.toString(), Log.DEBUG)
                           location?.let {presenter.updateCoordinates(location.latitude, location.longitude, token)}
                       }

           } catch (ex: RuntimeExecutionException)
           {

                val apiException = ex.cause as? ApiException
               when (apiException?.statusCode) {
                   LocationSettingsStatusCodes.RESOLUTION_REQUIRED ->
                   {
                       // Location settings are not satisfied. But could be fixed by showing the
                       // user a dialog.
                       try
                       {
                           // Cast to a resolvable exception.
                           val resolvable = apiException as ResolvableApiException
                           // Show the dialog by calling startResolutionForResult(),
                           // and check the result in onActivityResult().
                           resolvable.startResolutionForResult(
                                   this,
                                   REQUEST_CHECK_SETTINGS
                           )
                       }
                       catch (e: IntentSender.SendIntentException)
                       {
                           // Ignore the error.
                       }
                       catch (e: ClassCastException)
                       {
                           // Ignore, should be an impossible error.
                       }
                   }
                   LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE ->
                   {
                       // Location settings are not satisfied. However, we have no way to fix the
                       // settings so we won't show the dialog.
                   }
               }
           }
            catch (ex: Exception)
            {
            }
        }

    }

    @SuppressLint("MissingPermission")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?)
    {
        val states = LocationSettingsStates.fromIntent(data)
        LogTindogs( "Location states" + states.toString(), Log.DEBUG)
        when (requestCode)
        {
            REQUEST_CHECK_SETTINGS ->
                when (resultCode)
                {
                    Activity.RESULT_OK ->
                    {
                        val fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
                        fusedLocationClient.lastLocation
                                .addOnSuccessListener { location: Location? ->
                                    LogTindogs("Location: " + location.toString(), Log.DEBUG)
                                }
                    }
                    Activity.RESULT_CANCELED ->
                    {
                        Toast.makeText(this, getString(R.string.needYourLocation), Toast.LENGTH_SHORT).show()
                    }
                    else ->
                    {
                        Toast.makeText(this, getString(R.string.needYourLocation), Toast.LENGTH_SHORT).show()
                    }

                }

        }

    }
    @SuppressLint("MissingPermission")
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<String>, grantResults: IntArray)
    {
        when (requestCode)
        {
            PERMISSION_ACCESS_COARSE_LOCATION -> if (grantResults.any { it == PackageManager.PERMISSION_GRANTED })
            {
                displayLocationSettingsRequest(this)
            }
            else
            {
                Toast.makeText(this, getString(R.string.needYourLocation), Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun renderUser(userName: String, photo: String)
    {
        user_profile_name.text = userName
        Picasso.with(this)
                .load(photo)
                .into(user_profile_image)
    }

    override fun setUserProfileError(message: String)
    {
        setError(user_profile_main_view, message)
    }
}
