package com.picpay.desafio.android

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.desafio.domain.model.UserModel
import com.desafio.domain.usecases.UserUseCase
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.stub
import com.picpay.desafio.android.user.UserListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.*
import org.hamcrest.CoreMatchers
import org.hamcrest.MatcherAssert
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class UserListViewModelTest {

    private val useCase = mock<UserUseCase>()
    private lateinit var viewModel: UserListViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Before
    fun setupViewModel() {
        viewModel = UserListViewModel(testDispatcher, SavedStateHandle(), useCase)
    }

    @Test
    fun getUsers_returnUsers() = runBlockingTest {
         // given
        val user = UserModel("Teste", "ImgUrl", "Teste")

        useCase.stub {
            onBlocking { getLocalUsers() }.doReturn((arrayListOf(user)))
        }

        //when
        viewModel.getUses()
        val value = viewModel.users.getOrAwaitValue()

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
        viewModel.refresh()
        val value = viewModel.users.getOrAwaitValue()

        // Then
        MatcherAssert.assertThat(value[0].name, CoreMatchers.`is`("Teste"))
    }


}