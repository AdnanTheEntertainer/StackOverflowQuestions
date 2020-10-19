package com.callsigntask.queries.utils

object RequestParamManager {
    fun appParams(map :HashMap<String, String>): HashMap<String, String>{
        return hashMapOf(
            "page" to "1",
            "pagesize" to "30",
            "fromdate" to QueryDateUtils.getQueryDate(true),
            "todate" to QueryDateUtils.getQueryDate(false),
            "order" to "asc",
            "sort" to "votes",
            "tagged" to map["tag"]!!.trim().replace(",", ";").replace(" ", ";"),
            "min" to map.get("score")!!,
            "filter" to "default",
            "site" to "stackoverflow"
        )
    }
}