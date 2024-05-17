package com.example.transpapptest.domain.use_cases.order

import com.example.transpapptest.config.common.ApiResponse
import com.example.transpapptest.data.local.entities.relations.OrderAndStatus
import com.example.transpapptest.domain.repository.OrderRepository
import kotlinx.coroutines.flow.Flow

class GetAllOrders(
    private val repository: OrderRepository
) {
    suspend operator fun invoke(fetchFromRemote: Boolean): Flow<ApiResponse<List<OrderAndStatus>>> {
        return repository.getAllOrdersWithStatus(fetchFromRemote)
    }
}