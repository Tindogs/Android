package com.appvengers.business.models

import android.os.Parcelable
import java.io.Serializable


data class User(
        val _id: String,
        var firstName: String,
        var lastName:String,
          val email: String,
        var userName: String,
        var coordinates: Pair<Double, Double>?,
        val dogs: List<Dog>,
        var photo: String
): Serializable