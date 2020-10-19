package com.callsigntask.queries.viewmodel

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.callsigntask.queries.data.model.response.QueryResponse
import com.callsigntask.queries.data.model.response.QueryViewItem
import com.callsigntask.queries.data.model.response.toViewItem
import com.callsigntask.queries.data.network.ResponseState
import com.callsigntask.queries.data.repository.QueriesRepository
import kotlinx.coroutines.launch

class QueriesViewModel : ViewModel() {
    private val _queriesResponse = MutableLiveData<ResponseState<List<QueryViewItem>?>>()
    val queriesResponse: LiveData<ResponseState<List<QueryViewItem>?>> = _queriesResponse
    var queryPaging: LiveData<PagingData<QueryViewItem>>? = null


    fun getUnAnsweredQueries(score: Int, tag: String) {
        viewModelScope.launch {
            _queriesResponse.postValue(ResponseState.Loading(true))

            val response = QueriesRepository.getUnAnsweredQueries(
                hashMapOf(
                    "score" to score.toString(),
                    "tag" to tag
                )
            )
            if (response?.data != null) {
                val recipeList = (response.data as QueryResponse?)?.items?.map { it.toViewItem() }
                _queriesResponse.postValue(ResponseState.Success(recipeList as List<QueryViewItem>))
            } else if (response?.errorResponse != null) {
                _queriesResponse.postValue(ResponseState.Error(response.errorResponse))
            }
        }
    }

    fun getQueriesPaging(score: Int, tag: String) {
        queryPaging =
            QueriesRepository.getQueries(hashMapOf("score" to score.toString(), "tag" to tag))
                .cachedIn(viewModelScope)
                .map { it.map { it.toViewItem() } }
    }

}