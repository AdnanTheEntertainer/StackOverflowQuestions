package com.callsigntask.queries.data

import androidx.paging.DataSource
import androidx.paging.PagingSource
import com.callsigntask.queries.data.model.response.QueryItem
import com.callsigntask.queries.data.network.QueriesApiClient
import com.callsigntask.queries.data.network.QueriesService
import retrofit2.HttpException
import java.io.IOException

class QueryPageDataSource (val queryApiservice: QueriesService,
val map: HashMap<String, String>) : PagingSource<Int, QueryItem>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, QueryItem> {
        return   try {
            val pageNumber = params.key ?: 1
            map.put("page", pageNumber.toString())
            val response = queryApiservice.getUnAnsweredQueriesPageing(map)
            LoadResult.Page(
                data = response.items,
                prevKey = if (pageNumber == 1) null else 1,
                nextKey = if (response.items.isEmpty()) null else pageNumber +1
            )

        }
        catch (exception: IOException) {
            LoadResult.Error(exception)
        } catch (exception: HttpException) {
            LoadResult.Error(exception)
        }
    }

}