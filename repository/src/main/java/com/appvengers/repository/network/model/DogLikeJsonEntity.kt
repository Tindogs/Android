package com.appvengers.repository.network.model

import com.google.gson.annotations.SerializedName

data class DogLikeJsonEntity(
        @SerializedName("dog_like_id")
        val dogWhoLikes: String,
        @SerializedName("dog_name")
        val dogName: String,
        @SerializedName("owner_id")
        val ownerId: String,
        @SerializedName("owner_name")
        val ownerName: String)
