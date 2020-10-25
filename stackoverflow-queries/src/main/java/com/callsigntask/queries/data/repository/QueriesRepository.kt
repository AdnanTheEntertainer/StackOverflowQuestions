package com.callsigntask.queries.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.liveData
import com.callsigntask.queries.data.QueryPageDataSource
import com.callsigntask.queries.data.network.QueriesApiClient
import com.callsigntask.queries.data.network.QueriesService
import com.callsigntask.queries.data.network.ResponseModel
import com.callsigntask.queries.utils.RequestParamManager

object QueriesRepository : BaseRepository() {

    suspend fun getUnAnsweredQueries(queryParams: HashMap<String, String>): ResponseModel? {
        return safeApiCall {
            QueriesApiClient.buildService(QueriesService::class.java)
                .getUnAnsweredQueries(queryParams).await()
        }
    }

    fun getQueries(paraams: HashMap<String, String>) = Pager(
        config = PagingConfig(
            pageSize = 30,
            maxSize = 100,
            enablePlaceholders = false

        ),
        pagingSourceFactory = {
            QueryPageDataSource(
                map = RequestParamManager.appParams(map = paraams),
                queryApiservice = QueriesApiClient.buildService(QueriesService::class.java)
            )
        })
        .liveData
}