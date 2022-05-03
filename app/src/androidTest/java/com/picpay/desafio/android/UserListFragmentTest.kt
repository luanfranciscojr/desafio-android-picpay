package com.picpay.desafio.android

import android.view.View
import androidx.annotation.NonNull
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.desafio.domain.usecases.UserUseCase
import androidx.test.espresso.matcher.BoundedMatcher
import com.picpay.desafio.android.di.viewModelModule
import com.picpay.desafio.android.util.DataBindingIdlingResource
import com.picpay.desafio.android.util.EspressoIdlingResource
import com.picpay.desafio.android.util.monitorActivity
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.dsl.module
import org.koin.test.AutoCloseKoinTest
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.Description
import org.hamcrest.Matcher
import androidx.test.espresso.assertion.ViewAssertions.matches

@RunWith(AndroidJUnit4::class)
@MediumTest
@ExperimentalCoroutinesApi
class UserListFragmentTest: AutoCloseKoinTest()  {


    private val dataBindingIdlingResource = DataBindingIdlingResource()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        stopKoin()
        startKoin {
            modules(listOf(
             fakeUserUseCaseModule
            ,
                viewModelModule))
        }
    }

    @Before
    fun registerIdlingResource() {
        IdlingRegistry.getInstance().register(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().register(dataBindingIdlingResource)
    }

    @After
    fun unregisterIdlingResource() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.countingIdlingResource)
        IdlingRegistry.getInstance().unregister(dataBindingIdlingResource)
    }

    @Test
    fun userList_WithItems() {

        val activityScenario = ActivityScenario.launch(MainActivity::class.java)
        dataBindingIdlingResource.monitorActivity(activityScenario)

        runBlocking {
            onView(withId(R.id.recyclerView))
                .check(matches(atPosition(0, withText("Teste"), R.id.username)));
        }

    }

    fun atPosition(
        position: Int, itemMatcher: Matcher<View?>,
        @NonNull targetViewId: Int
    ): Matcher<View?>? {
        return object : BoundedMatcher<View?, RecyclerView>(RecyclerView::class.java) {
            override fun describeTo(description: Description) {
                description.appendText("has view id $itemMatcher at position $position")
            }

            override fun matchesSafely(recyclerView: RecyclerView): Boolean {
                val viewHolder = recyclerView.findViewHolderForAdapterPosition(position)
                val targetView: View = viewHolder!!.itemView.findViewById(targetViewId)
                return itemMatcher.matches(targetView)
            }
        }
    }


}