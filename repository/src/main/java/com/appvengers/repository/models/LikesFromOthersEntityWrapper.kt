package com.appvengers.repository.models


data class LikesFromOthersEntityWrapper(val dogLikeId: String,
                                        val dogName: String,
                                        val ownerId: String,
                                        val ownerName: String) {
}