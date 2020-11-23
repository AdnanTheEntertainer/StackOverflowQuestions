package com.callsigntask.queries.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.callsigntask.queries.databinding.ItemQueriesLoadStateFooterBinding

class QueryLoadStateAdapter(val retry: () -> Unit) : LoadStateAdapter<QueryLoadStateAdapter.QueryLoadStateViewHolder> (){

    override fun onBindViewHolder(holder: QueryLoadStateViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): QueryLoadStateViewHolder {
        return QueryLoadStateViewHolder(ItemQueriesLoadStateFooterBinding.inflate(LayoutInflater.from(parent.context),
        parent,
        false)
        )
    }

    inner class QueryLoadStateViewHolder(private val binding: ItemQueriesLoadStateFooterBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(loadState: LoadState) {
            binding.apply {
                binding.progressBar.isVisible = loadState is LoadState.Loading
                binding.buttonRetry.isVisible = loadState !is LoadState.Loading
                binding.textViewError.isVisible = loadState !is LoadState.Loading
            }
           if (loadState is LoadState.Error)
               binding.textViewError.text = loadState.error.localizedMessage
        }

        init {
            binding.buttonRetry.setOnClickListener {
                retry.invoke()
            }
        }
    }

}