package com.callsigntask.queries.ui.activity

import android.os.Bundle
import android.view.MenuItem
import com.callsigntask.queries.R
import com.callsigntask.queries.ui.base.DatabindingActivity
import com.callsigntask.queries.ui.fragment.InputFormFragment
import com.callsigntask.queries.utils.FragmentUtils
import com.microsoft.appcenter.AppCenter
import com.microsoft.appcenter.analytics.Analytics
import com.microsoft.appcenter.crashes.Crashes


class CallSignActivity : DatabindingActivity() {

//    private val binding: ActivityCallSignBinding by binding(R.layout.activity_call_sign)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call_sign)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    AppCenter.start(
        application, "1cad3416-bcf4-4e38-92aa-96c517124520",
        Analytics::class.java, Crashes::class.java
    )
    if(supportFragmentManager.fragments.size == 0) {
        FragmentUtils.addFragment(
            this,
            InputFormFragment.newInstance(),
            R.id.container
        )
    }
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount>1)
            supportFragmentManager.popBackStack()
            else
        super.onBackPressed()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            onBackPressed()
        return super.onOptionsItemSelected(item)
    }
}