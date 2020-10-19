package com.recipe.task.interactor

import com.callsigntask.queries.data.model.response.QueryViewItem

interface QueryItemClickListener {
    fun onQueryListClicked(query: QueryViewItem?)
}