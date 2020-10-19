package com.callsigntask.queries.ui.fragment

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.callsigntask.queries.R
import com.callsigntask.queries.ui.adapter.QueriesAdapter
import com.callsigntask.queries.utils.EspressoIdlingResourceRule
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@RunWith(AndroidJUnit4::class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class QueriesListFragmentTest {

    @get:Rule
    var idlingResour = EspressoIdlingResourceRule()

    @Before
    fun launch_fragment() {
        val scenario = launchFragmentInContainer<QueriesListFragment>()
    }

    @Test
    fun should_be_hide_try_button() {
        Espresso.onView(ViewMatchers.withId(R.id.bt_try_again))
            .check(ViewAssertions.matches(Matchers.not(ViewMatchers.isDisplayed())))
    }

    @Test
    fun b_check_recyclerview_is_visible() {
        Espresso.onView(withId(R.id.rv_query))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Espresso.onView(withId(R.id.pb_loading))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun scrollToItem_checkItsText() {
        // First, scroll to the position that needs to be matched and click on it.
        val itemElementText =
            "Showing ToastAndroid when app is closed with react-native and Headless JS"
        Espresso.onView(withId(R.id.rv_query))
            .perform(
                RecyclerViewActions.actionOnItem<QueriesAdapter.QueryViewHolder>(
                    hasDescendant(
                        withText(itemElementText)
                    ), ViewActions.click()
                )
            )
    }

    @Test
    fun scrollToItem_click() {
        // First, scroll to the position that needs to be matched and click on it.
        Espresso.onView(withId(R.id.rv_query))
            .perform(RecyclerViewActions.scrollToPosition<QueriesAdapter.QueryViewHolder>(3))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<QueriesAdapter.QueryViewHolder>(
                    3, ViewActions.click()
                )
            )
    }
}