package com.recipe.task.view.base

import androidx.recyclerview.widget.RecyclerView


abstract class BaseRecyclerViewAdapter<T, VH : BaseViewHolder<T>>() :
    RecyclerView.Adapter<VH>() {
    private var items: ArrayList<T>? = ArrayList()

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items?.get(position)
        holder.onBind(item)
    }

    override fun getItemCount(): Int {
        return items?.size ?: 0
    }

    open fun setData(items: List<T>?) {
        items?.let {
            this.items?.clear()
            this.items?.addAll(items)
            notifyDataSetChanged()
        }
    }

    fun getItems(): List<T>? {
        return items
    }


    fun getItem(position: Int): T? {
        return items?.get(position)
    }

    open fun addItem(item: T?) {
        item?.let {
            this.items?.add(item)
            notifyItemInserted(items?.size ?: 1 - 1)
        }
    }

    open fun addAll(items: List<T>?) {
        items?.let {
            this.items!!.addAll(items)
            notifyItemRangeInserted(this.items?.size ?: 1 - items.size, items.size)
        }
    }

    open fun clear() {
        items?.clear()
        notifyDataSetChanged()
    }

    open fun remove(item: T?) {
        item?.let {
            val position = items?.indexOf(item) ?: -1
            if (position > -1) {
                items!!.removeAt(position)
                notifyItemRemoved(position)
            }
        }
    }

    open fun isEmpty(): Boolean {
        return itemCount == 0
    }
}