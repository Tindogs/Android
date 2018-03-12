package com.appvengers.repository.network

import com.appvengers.repository.network.model.DogJsonEntity
import com.appvengers.repository.network.model.DogLikeJsonEntity
import com.appvengers.repository.network.model.QueryJsonEntity
import com.appvengers.repository.network.model.UserJsonEntity

internal class NetworkEntitiesFetcherImpl(private val networkManager: NetworkManager): NetworkEntitiesFetcher
{
    override fun getUser(username: String, password: String, success: (user: UserJsonEntity) -> Unit, error: (message: String) -> Unit)
    {
        val query = QueryJsonEntity(1.0, 3.0, 1.0, false,"")
        val dogLikeEntity = DogLikeJsonEntity(5, "PerreteLike")
        val dog = DogJsonEntity(1,"Perrete2", 4.0, "Cocker", false, "Marron", "", listOf(), query, listOf(dogLikeEntity))
        val user = UserJsonEntity(2, "Pepito", "Perez", "66666666", "777777777", "pepito@gmail.com", "user1", Pair(43.6, -3.6), listOf(dog))
        success(user)
    }
}