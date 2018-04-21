package com.appvengers.business.interactors

import android.util.Log
import com.appvengers.business.mappers.map
import com.appvengers.business.mappers.mapToDog
import com.appvengers.business.models.Dog
import com.appvengers.repository.Repository

class GetDogsListInteractorImpl(private val repository: Repository) : GetDogsListInteractor {
    override fun execute(userId: String, dogId: String, token: String, success: (dogs: MutableList<Dog>) -> Unit, error: (msg: String) -> Unit) {
        repository.getDogList(userId,dogId,token,{
            Log.d("INTERACTOR", it.toString())
            if(it.count() > 0) {
                var resultDogs = mutableListOf<Dog>()
                it.forEach({
                    resultDogs.add(it.mapToDog())
                })
                success(resultDogs)
            } else {
                error("No hay perretes cerca tuya, prueba otra vez más adelante")
            }
        },{
            error(it)
        })
    }
}