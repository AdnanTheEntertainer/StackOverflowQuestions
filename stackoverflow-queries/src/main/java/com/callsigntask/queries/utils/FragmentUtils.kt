package com.callsigntask.queries.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

object FragmentUtils {
    fun addFragment(activity: AppCompatActivity?, fragment: Fragment, containerId:Int) {
        activity?.supportFragmentManager?.beginTransaction()
        ?.replace(containerId, fragment, fragment.javaClass.name)
        ?.commit()
    }

    fun addFragmentWithStack(activity: FragmentActivity?, fragment: Fragment, containerId:Int) {
        /*if(activity == null || activity.isFinishing)
            return*/
        activity?.supportFragmentManager?.beginTransaction()
        ?.replace(containerId, fragment, fragment.javaClass.name)
        ?.addToBackStack(fragment.javaClass.name)
        ?.commit()
    }
}