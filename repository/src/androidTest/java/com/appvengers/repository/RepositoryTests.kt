package com.appvengers.repository

import android.content.Context
import android.support.test.InstrumentationRegistry
import com.appvengers.repository.di.RepositoryObjectInjector
import com.appvengers.repository.models.UserEntityWrapper
import org.junit.Test
import java.lang.ref.WeakReference
import com.nhaarman.mockito_kotlin.*
import org.junit.runner.RunWith
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
class RepositoryTests
{
    val appContext = InstrumentationRegistry.getTargetContext()!!
    val objectInjector = RepositoryObjectInjector(WeakReference<Context>(appContext))

    @Test
    @Throws(Exception::class)
    fun test_DeleteAllUsers_when_deleteAll_NoErrorReturned()
    {
        val repository = objectInjector.buildRepository()

        val success = mock<(user: UserEntityWrapper) -> Unit>()
        val error = mock<(message: String) -> Unit>()
        repository.getUser("abs", success, error)

        verify(success, times(1)).invoke(any())
        verify(error, never()).invoke(any<String>())

    }
}