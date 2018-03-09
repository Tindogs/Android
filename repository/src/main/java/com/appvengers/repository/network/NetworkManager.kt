package com.appvengers.repository.network

internal interface NetworkManager
{
    fun execute(url:String, success: (data:String) -> Unit, error: (message: String) -> Unit)
}