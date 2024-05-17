package com.example.transpapptest.domain.repository

import com.example.transpapptest.config.common.ApiResponse
import com.example.transpapptest.data.remote.payload.response.customer.CustomerInfoResponse
import kotlinx.coroutines.flow.Flow

interface CustomerRepository {
    fun loadCustomer(
        fetchFromRemote: Boolean,
        id: Long
    ): Flow<ApiResponse<CustomerInfoResponse>>
}