package com.appvengers.tindogs.di

import android.content.Context
import com.appvengers.business.di.BusinessObjectInjector
import com.appvengers.business.interactors.*
import com.appvengers.business.interactors.dogCRUD.CreateDogInteractor
import com.appvengers.business.interactors.userCRUD.CreateUserInteractor
import com.appvengers.business.interactors.userCRUD.GetUserInteractor
import com.appvengers.business.interactors.userCRUD.UpdateUserInteractor

object ObjectInjector
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

    fun buildUpdateUserInteractor(context: Context): UpdateUserInteractor
    {
        val businessObjectInjector = BusinessObjectInjector(context)
        return businessObjectInjector.updateUserInteractor()
    }

    fun buildCreateDogInteractor(context: Context): CreateDogInteractor
    {
        val businessObjectInjector = BusinessObjectInjector(context)
        return businessObjectInjector.createDogInteractor()
    }

    fun buildUploadUserPhoto(context: Context): UploadUserPhotoInteractor {
        val bussinessObjectInjector = BusinessObjectInjector(context)
        return bussinessObjectInjector.uploadUserPhotoInteractor()
    }

    fun buildDogMatchInteractor(context: Context) : GetDogsListInteractor {
        val businessObjectInjector = BusinessObjectInjector(context)
        return businessObjectInjector.getDogListMatchInteractor()
    }

    fun buildNewDogLikeInteractor(context: Context) : NewDogLikeInteractor {
        val businessObjectInjector = BusinessObjectInjector(context)
        return businessObjectInjector.getNewDogLikeInteractor()
    }

    fun buidNewDogDislikeInteractor(context: Context) : NewDogDislikeInteractor {
        val businessObjectInjector = BusinessObjectInjector(context)
        return businessObjectInjector.getNewDogDislkeInteractor()
    }

    fun buildGetDogDetailInteractor(context: Context) : GetDogDetailInteractor {
        val businessObjectInjector = BusinessObjectInjector(context)
        return businessObjectInjector.getDogDetailInteractor()
    }



}