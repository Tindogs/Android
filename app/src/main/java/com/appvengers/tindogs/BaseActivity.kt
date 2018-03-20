package com.appvengers.tindogs

import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import com.appvengers.utils.PreferencesRepository
import java.lang.ref.WeakReference

open class BaseActivity: AppCompatActivity()
{
    fun setUpToolbar(homeIsEnabled: Boolean = false) {

        if (homeIsEnabled) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.setDisplayShowHomeEnabled(true)
        }
    }

    fun setTitle(title: String) {
        supportActionBar?.title = title
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                onBackPressed()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    fun setError(view: View, message: String)
    {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG).show()
    }

    fun getTokenAndUserId(): Pair<String, String>?
    {
        val preferences = PreferencesRepository(WeakReference(this))
        return preferences.getTokenAndUserId(getString(R.string.sharedPerferencesFileName), getString(R.string.sharedPreferencesToken), getString(R.string.sharedPreferencesUserId))
    }
}