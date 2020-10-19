package com.callsigntask.queries.data.repository

import com.callsigntask.queries.data.network.BaseResult
import com.callsigntask.queries.data.network.ResponseModel
import retrofit2.Response


open class BaseRepository {

    suspend fun <T : Any> safeApiCall(call: suspend () -> Response<T>): ResponseModel? {


        val result: BaseResult<T> = safeApiResult(call)
        var data: ResponseModel? = null

        data = when (result) {
            is BaseResult.Success ->
                ResponseModel(result.data, null)
            is BaseResult.Error -> {
                ResponseModel(null, result.errorResponse)
            }
        }

        return data

    }

    private suspend fun <T : Any> safeApiResult(call: suspend () -> Response<T>): BaseResult<T> {
        try {
            val response = call.invoke()
            if (response.isSuccessful) {
                return BaseResult.Success(response.body()!!)
            }


        } catch (exception: Exception) {
            exception.printStackTrace()
            return BaseResult.Error(exception.localizedMessage)
        }
        return BaseResult.Error("Something went wrong")

    }
}