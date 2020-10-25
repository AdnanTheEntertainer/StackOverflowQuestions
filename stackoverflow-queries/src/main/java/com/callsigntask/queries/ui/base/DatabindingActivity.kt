package com.callsigntask.queries.ui.base

import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

abstract class DatabindingActivity: AppCompatActivity() {

    protected inline fun <reified T: ViewDataBinding> binding (
        @LayoutRes res : Int
    ): Lazy<T> = lazy { DataBindingUtil.setContentView<T>(this,res)}
}