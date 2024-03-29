package com.beettechnologies.lloyds.common.data.repository

import com.beettechnologies.lloyds.common.data.model.ApiResponse
import com.beettechnologies.lloyds.common.data.model.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

abstract class NetworkResource<ResultType, RequestType> {

    fun asFlow() = flow<Resource<ResultType>> {
        emit(Resource.loading())

        when (val apiResponse = createNetworkRequest()) {
            is ApiResponse.ApiSuccessResponse -> {
                val flow = loadResults(processResponse(apiResponse))
                    .map { Resource.success(it) }
                emitAll(flow)
            }

            is ApiResponse.ApiEmptyResponse -> {
                val flow = loadResults()
                    .map { Resource.success(it) }
                emitAll(flow)
            }

            is ApiResponse.ApiErrorResponse -> {
                emit(Resource.error(apiResponse.errorMessage))
            }
        }
    }

    protected open fun processResponse(response: ApiResponse.ApiSuccessResponse<RequestType>) =  response.body

    protected abstract suspend fun loadResults(item: RequestType? = null): Flow<ResultType>

    protected abstract suspend fun createNetworkRequest(): ApiResponse<RequestType>
}