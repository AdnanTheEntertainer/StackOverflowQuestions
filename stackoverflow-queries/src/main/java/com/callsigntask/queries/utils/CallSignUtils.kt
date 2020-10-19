package com.callsigntask.queries.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

object CallSignUtils {
    fun hideSoftKeyboard(activity: Activity?) {
       /* if (activity.currentFocus == null){
            return
        }*/
        val inputMethodManager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(activity.currentFocus?.windowToken, 0)
    }
}