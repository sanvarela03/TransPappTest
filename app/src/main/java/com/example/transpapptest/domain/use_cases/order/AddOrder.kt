package com.example.transpapptest.domain.use_cases.order

import com.example.transpapptest.config.common.ApiResponse
import com.example.transpapptest.data.local.entities.relations.OrderAndStatus
import com.example.transpapptest.data.remote.payload.request.AddOrderRequest
import com.example.transpapptest.data.remote.payload.response.MessageResponse
import com.example.transpapptest.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow

class AddOrder(
    private val repository: OrderRepository
) {
    suspend operator fun invoke(addOrderRequest: AddOrderRequest): Flow<ApiResponse<MessageResponse>> {
        return repository.addOrder(addOrderRequest)
    }
}