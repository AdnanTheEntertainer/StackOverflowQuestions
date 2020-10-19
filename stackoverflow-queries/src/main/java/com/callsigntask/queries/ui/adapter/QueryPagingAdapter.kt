package com.callsigntask.queries.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.callsigntask.queries.data.model.response.QueryViewItem
import com.callsigntask.queries.databinding.ItemQueriesListBinding
import com.recipe.task.interactor.QueryItemClickListener
import com.recipe.task.view.base.BaseViewHolder

class QueryPagingAdapter(val clickListener: QueryItemClickListener? = null): PagingDataAdapter<QueryViewItem, BaseViewHolder<QueryViewItem>>(QUESTION_COMPARATOR) {

    override fun onBindViewHolder(holder: BaseViewHolder<QueryViewItem>, position: Int) {
       val item = getItem(position)
        holder.onBind(item)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder<QueryViewItem> {
        return QueryViewHolder(ItemQueriesListBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }

    inner class QueryViewHolder (private val binding: ItemQueriesListBinding) :
        BaseViewHolder<QueryViewItem>(binding) {
        init {
            binding.lyQuery.setOnClickListener{
                val position = bindingAdapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val item = getItem(position)
                    if (item != null) {
                        clickListener?.onQueryListClicked(item)
                    }
                }
            }
        }

        override fun onBind(model: QueryViewItem?) {
            binding.queryItem = model
            binding.executePendingBindings()
        }
    }

    companion object {
        private val QUESTION_COMPARATOR = object : DiffUtil.ItemCallback<QueryViewItem>() {
            override fun areItemsTheSame(oldItem: QueryViewItem, newItem: QueryViewItem) =
                oldItem.questionId == newItem.questionId

            override fun areContentsTheSame(oldItem: QueryViewItem, newItem: QueryViewItem) =
                oldItem == newItem
        }
    }
}