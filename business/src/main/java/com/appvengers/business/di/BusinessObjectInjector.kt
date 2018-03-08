package com.appvengers.business.di

import android.content.Context
import com.appvengers.business.interactors.userCRUD.*
import com.appvengers.repository.di.RepositoryObjectInjector
import java.lang.ref.WeakReference


class BusinessObjectInjector(context: Context)
{
    private val repository = RepositoryObjectInjector(WeakReference(context)).buildRepository()
    fun getUserInteractor(): GetUserInteractor
    {
        return GetUserInteractorImpl(repository)
    }
    fun createUserInteractor(): CreateUserInteractor
    {
        return CreateUserInteractorFake()
    }
}