package com.callsigntask.queries.data.network

import com.callsigntask.queries.data.model.response.QueryResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.QueryMap

interface QueriesService {
    @GET("questions/unanswered")
    fun getUnAnsweredQueries(@QueryMap map: HashMap<String, String>): Deferred<Response<QueryResponse>>

    @GET("questions/unanswered")
    suspend fun getUnAnsweredQueriesPageing(@QueryMap map: HashMap<String, String>): QueryResponse
}