package com.appvengers.repository

import android.content.Context
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import com.appvengers.repository.di.RepositoryObjectInjector
import com.appvengers.repository.models.*
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import java.lang.ref.WeakReference

@RunWith(AndroidJUnit4::class)
class DaoTests
{
    val appContext = InstrumentationRegistry.getTargetContext()!!
    val objectInjector = RepositoryObjectInjector(WeakReference<Context>(appContext))

    @Test
    @Throws(Exception::class)
    fun test_DeleteAllUsers_when_deleteAll_NoErrorReturned()
    {
        val userDao = objectInjector.buildUserDaoPersistable()

        val result = userDao.deleteAll()

        assertTrue(result)

    }

    @Test
    @Throws(Exception::class)
    fun test_insertUser_when_valid_user_returns_correct_id()
    {
        val userDao = objectInjector.buildUserDaoPersistable()

        val deleteResult = userDao.deleteAll()

        assertTrue(deleteResult)

        val user = getBasicUserEntity()
        val id = userDao.insert(user)

        assertEquals(user._id, id)
    }

    @Test
    @Throws(Exception::class)
    fun test_insertDog_when_valid_dog_returns_correct_id()
    {
        val dogDao = objectInjector.buildDogDaoPersistable()

        val deleteResult = dogDao.deleteAll()

        assertTrue(deleteResult)

        val dog = getDog("abcd")

        val id = dogDao.insert(dog)

        assertEquals(dog._id, id)
    }

    private fun getBasicUserEntity(): UserEntityWrapper
    {
        return UserEntityWrapper(
                "abc",
                "Pepito",
                "Perez",
                "pepito@gmail.com",
                "user1",
                Pair(43.6, -3.6),
                listOf(getDog("abc")))
    }

    private fun getDog(user:UserEntityWrapper): DogEntityWrapper
    {
        return DogEntityWrapper(
                "abc",
                "Perrete",
                4.0,
                "Cocker",
                false,
                "Marron",
                "" ,
                listOf("Foto1", "Foto2"),
                QueryEntityWrapper(1.0, 2.0, false, ""),
                listOf(DogLikeEntityWrapper("abcd", "PerreteLike")), user._id)
    }

    private fun getDog(userId: String): DogEntityWrapper
    {
        return DogEntityWrapper(
                "abc",
                "Perrete",
                4.0,
                "Cocker",
                false,
                "Marron",
                "" ,
                listOf("Foto1", "Foto2"),
                QueryEntityWrapper(1.0, 2.0, false, ""),
                listOf(DogLikeEntityWrapper("abcd", "PerreteLike")), userId)
    }
}