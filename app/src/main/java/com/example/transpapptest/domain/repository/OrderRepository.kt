package com.example.transpapptest.domain.repository

import com.example.transpapptest.config.common.ApiResponse
import com.example.transpapptest.data.local.entities.AddressEntity
import com.example.transpapptest.data.local.entities.relations.OrderAndStatus
import com.example.transpapptest.data.remote.payload.request.AddOrderRequest
import com.example.transpapptest.data.remote.payload.response.MessageResponse
import kotlinx.coroutines.flow.Flow

interface OrderRepository {
    suspend fun getAllOrdersWithStatus(
        fetchFromRemote: Boolean
    ): Flow<ApiResponse<List<OrderAndStatus>>>

    suspend fun addOrder(addOrderRequest: AddOrderRequest): Flow<ApiResponse<MessageResponse>>
}