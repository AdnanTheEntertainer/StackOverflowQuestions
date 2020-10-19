package com.callsigntask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.callsigntask.queries.ui.activity.CallSignActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startSdk(view: View) {
        startActivity(Intent(this, CallSignActivity::class.java))
    }
}