package com.callsigntask.queries.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity

object FragmentUtils {
    fun addFragment(activity: AppCompatActivity?, fragment: Fragment, containerId:Int) {
        val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.add(containerId, fragment, fragment.javaClass.name)
        fragmentTransaction?.commit()
    }

    fun addFragmentWithStack(activity: FragmentActivity?, fragment: Fragment, containerId:Int) {
        if(activity == null || activity.isFinishing)
            return
        val fragmentTransaction = activity.supportFragmentManager.beginTransaction()
        fragmentTransaction.add(containerId, fragment, fragment.javaClass.name)
        fragmentTransaction.addToBackStack(fragment.javaClass.name)
        fragmentTransaction.commit()
    }
}