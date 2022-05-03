package com.desafio.domain

import com.desafio.domain.model.UserModel
import com.desafio.domain.repositories.UserRepository
import com.desafio.domain.usecases.UserUseCase
import com.desafio.domain.usecases.UserUseCaseImpl
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class UserUseCaseTest {

    private val repository = mock<UserRepository>()
    private lateinit var useCase: UserUseCase

    @Before
    fun setupUseCase() {
        useCase = UserUseCaseImpl(repository)
    }

    @Test
    fun getUsers_returnUsers() = runBlockingTest {
        // given
        val user = UserModel("Teste", "ImgUrl", "Teste")

        useCase.stub {
            onBlocking { getLocalUsers() }.doReturn((arrayListOf(user)))
        }

        //when
        val value  = repository.getUsers()

        // Then
        MatcherAssert.assertThat(value[0].name, CoreMatchers.`is`("Teste"))
    }

    @Test
    fun refresh_returnUsers() = runBlockingTest {
        // given
        val user = UserModel("Teste", "ImgUrl", "Teste")

        useCase.stub {
            onBlocking { getLocalUsers() }.doReturn((arrayListOf(user)))
        }

        //when
        repository.refresh()
        val value = repository.getUsers()

        // Then
        MatcherAssert.assertThat(value[0].name, CoreMatchers.`is`("Teste"))
    }

}