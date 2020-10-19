package com.callsigntask.queries.data.model.response

class QueryViewItem (
    var title : String,
    var score: Int,
    var link: String,
    var questionId: Int
){
    override fun equals(other: Any?): Boolean {
        other as QueryViewItem
        return title == other.title &&
                score == other.score &&
                link == other.link &&
                questionId == other.questionId
    }
}