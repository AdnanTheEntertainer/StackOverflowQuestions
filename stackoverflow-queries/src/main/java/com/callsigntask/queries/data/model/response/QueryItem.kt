package com.callsigntask.queries.data.model.response

import com.google.gson.annotations.SerializedName

data class QueryItem(

    @SerializedName("tags") val tags: List<String> = listOf(),
    @SerializedName("is_answered") val is_answered: Boolean? = false,
    @SerializedName("view_count") val view_count: Int? = 0,
    @SerializedName("answer_count") val answer_count: Int? = 0,
    @SerializedName("score") val score: Int? = 0,
    @SerializedName("last_activity_date") val last_activity_date: Long? = 0L,
    @SerializedName("creation_date") val creation_date: Long? = 0L,
    @SerializedName("last_edit_date") val last_edit_date: Long? = 0L,
    @SerializedName("question_id") var question_id: Int? = 0,
    @SerializedName("content_license") val content_license: String? = "",
    @SerializedName("link") val link: String? = "",
    @SerializedName("title") val title: String? = ""
)
// Optimize model for UI population
fun QueryItem.toViewItem() = QueryViewItem(
    title = this.title ?: "",
    score = this.score ?: 0,
    link = this.link ?: "",
    questionId = question_id ?: 0
)
