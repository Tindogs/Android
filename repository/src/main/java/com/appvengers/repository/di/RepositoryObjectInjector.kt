package com.appvengers.repository.di

import android.content.Context
import com.appvengers.repository.Repository
import java.lang.ref.WeakReference

class RepositoryObjectInjector(private val weakContext: WeakReference<Context>)
{
    fun buildRepository(): Repository
    {
        TODO("Acordarse del framework greendao")
    }
}