package com.recipe.task.view.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder<T>(binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {

    open fun onBind(model: T?){}
}