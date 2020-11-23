package com.callsigntask.utils

import androidx.annotation.VisibleForTesting
import androidx.test.espresso.idling.CountingIdlingResource

object EspressoIdlingResource {
    private const val RESOURCE= "GLOBAL_CALL_SIGN"

    @VisibleForTesting
    @JvmField val countingIdlingResource = CountingIdlingResource(RESOURCE)

    fun increment() {
    }

    fun decrement() {

    }
}