package com.appvengers.business.di

import android.content.Context
import com.appvengers.business.interactors.dogCRUD.CreateDogInteractor
import com.appvengers.business.interactors.dogCRUD.CreateDogInteractorImpl
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
        return CreateUserInteractorImpl(repository)
    }

    fun updateUserInteractor(): UpdateUserInteractor
    {
        return UpdateUserInteractorImpl(repository)
    }

    fun createDogInteractor(): CreateDogInteractor
    {
        return CreateDogInteractorImpl(repository)
    }
}