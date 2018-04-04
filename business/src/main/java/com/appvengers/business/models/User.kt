package com.appvengers.business.models

import android.os.Parcelable
import java.io.Serializable


data class User(
        val _id: String,
        val firstName: String,
        val lastName:String,
          val email: String,
        val userName: String,
        var coordinates: Pair<Double, Double>?,
        val dogs: List<Dog>
): Serializable