package com.callsigntask.queries.data.network

sealed class BaseResult<out T: Any> {
    data class Success<out T : Any>(val data: T) : BaseResult<T>()
    data class Error(val errorResponse: String?) : BaseResult<Nothing>()
}