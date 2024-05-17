package com.example.transpapptest.domain.use_cases.customer

import com.example.transpapptest.config.common.ApiResponse
import com.example.transpapptest.data.remote.payload.response.customer.CustomerInfoResponse
import com.example.transpapptest.domain.repository.CustomerRepository
import kotlinx.coroutines.flow.Flow

class GetCustomer(
    private val repository: CustomerRepository
) {
    operator fun invoke(
        fetchFromRemote: Boolean,
        id: Long
    ): Flow<ApiResponse<CustomerInfoResponse>> {
        return repository.loadCustomer(fetchFromRemote, id)
    }
}