package com.callsigntask.queries.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.callsigntask.queries.R
import com.callsigntask.queries.ui.fragment.InputFormFragment
import com.callsigntask.queries.utils.FragmentUtils

class CallSignActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_call_sign)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        FragmentUtils.addFragment(
            this,
            InputFormFragment.newInstance(),
            R.id.container
        )
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