package com.callsigntask

import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest{

    @get:Rule
    var activityRule = ActivityScenarioRule(  MainActivity::class.java)

    @Test
    fun verify_launch_sdk() {
        Espresso.onView(withId(R.id.bt_launch))
            .perform(click())
    }
}