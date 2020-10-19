package com.callsigntask.queries.data.model.response

import com.google.gson.annotations.SerializedName

data class QueryResponse(
    @SerializedName("items") val items : List<QueryItem> = listOf() )
