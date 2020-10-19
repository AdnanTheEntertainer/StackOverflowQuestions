package com.callsigntask.queries.ui.fragment

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import com.callsigntask.queries.R
import com.callsigntask.queries.ui.activity.CallSignActivity
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(androidx.test.ext.junit.runners.AndroidJUnit4::class)
class InputFormFragmentTest {

    @get:Rule
    val activityRule = ActivityScenarioRule(CallSignActivity::class.java)

    @Before
    fun launch_fragment() {
        val scenario = launchFragmentInContainer<InputFormFragment>()
    }

    @Test
    fun verify_default_score() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Espresso.onView(withId(R.id.et_score))
            .check(ViewAssertions.matches(ViewMatchers.withText(appContext.getString(R.string.default_score))))
    }

    @Test
    fun should_fail_default_score() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Espresso.onView(withId(R.id.et_score))
            .check(ViewAssertions.matches(ViewMatchers.withText("3")))
    }

    @Test
    fun verify_default_tag() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        Espresso.onView(withId(R.id.et_tag))
            .check(ViewAssertions.matches(ViewMatchers.withText(appContext.getString(R.string.default_tag))))
    }

    @Test
    fun submit_button_is_visibled() {
        Espresso.onView(withId(R.id.bt_submit))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }


    @Test
    fun verifyUserLogin() {
        Espresso.onView(withId(R.id.et_score))
            .perform(clearText())
            .perform(ViewActions.typeText("4"), ViewActions.closeSoftKeyboard())
            .check(ViewAssertions.matches(ViewMatchers.withText("4")))


        Espresso.onView(withId(R.id.et_tag))
            .perform(clearText())
            .perform(ViewActions.typeText("kotlin"), ViewActions.closeSoftKeyboard())
            .check(ViewAssertions.matches(ViewMatchers.withText("kotlin")))

        Espresso.onView(withId(R.id.bt_submit))
            .perform(click())

    }

}