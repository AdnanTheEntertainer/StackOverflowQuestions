package com.callsigntask.queries.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.callsigntask.queries.R
import com.callsigntask.queries.data.model.response.QueryViewItem
import com.callsigntask.queries.databinding.ItemQueriesListBinding
import com.recipe.task.interactor.QueryItemClickListener
import com.recipe.task.view.base.BaseRecyclerViewAdapter
import com.recipe.task.view.base.BaseViewHolder

class QueriesAdapter(
    private val context: Context?,
    val clickListener: QueryItemClickListener? =null
) :
    BaseRecyclerViewAdapter<QueryViewItem, BaseViewHolder<QueryViewItem>>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<QueryViewItem> {
        val layoutInflater = LayoutInflater.from(context)
        return QueryViewHolder(
            DataBindingUtil.inflate(
                layoutInflater,
                R.layout.item_queries_list,
                parent,
                false
            )
        )
    }


    inner class QueryViewHolder (private val binding: ItemQueriesListBinding) :
        BaseViewHolder<QueryViewItem>(binding) {
        init {
            binding.lyQuery.setOnClickListener{
                clickListener?.onQueryListClicked(getItems()?.get(adapterPosition))
            }
        }

        override fun onBind(model: QueryViewItem?) {
            binding.queryItem = model
            binding.executePendingBindings()
        }
    }
}