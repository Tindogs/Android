package com.appvengers.business.interactors

import android.util.Log
import com.appvengers.business.models.Dog
import com.appvengers.business.models.Query

class GetDogsListInteractorFakeImpl : GetDogsListInteractor {
    override fun execute(dogId: String, success: (dogs: MutableList<Dog>) -> Unit, error: (msg: String) -> Unit) {
        var dogs = createDogsList()
        Log.d("APP", "Paso")
        success(dogs)
    }

    private fun createDogsList(): MutableList<Dog> {
        return mutableListOf(Dog("","Prueba 1",2.0,"Adili",true,"Rojo","dekoekoe koeko", listOf(), Query(2.0,200.0,true,"Adili"), listOf()),
                Dog("","Prueba 2",2.0,"Adili",true,"Rojo","dekoekoe koeko", listOf(), Query(2.0,200.0,true,"Adili"), listOf()),
                Dog("","Prueba 3",2.0,"Adili",true,"Rojo","dekoekoe koeko", listOf(), Query(2.0,200.0,true,"Adili"), listOf()))
    }
}