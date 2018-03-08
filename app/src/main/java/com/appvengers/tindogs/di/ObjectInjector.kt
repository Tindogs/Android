package com.appvengers.tindogs.di

import android.content.Context
import com.appvengers.business.di.BusinessObjectInjector
import com.appvengers.business.interactors.userCRUD.CreateUserInteractor
import com.appvengers.business.interactors.userCRUD.GetUserInteractor

class ObjectInjector
{
    fun buildGetUserInteractor(context: Context): GetUserInteractor
    {
         val businessObjectInjector = BusinessObjectInjector(context)
        return businessObjectInjector.getUserInteractor()
    }

    fun buildCreateUserInteractor(context: Context): CreateUserInteractor
    {
        val businessObjectInjector = BusinessObjectInjector(context)
        return businessObjectInjector.createUserInteractor()
    }
}