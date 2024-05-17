package com.example.transpapptest.domain.use_cases.address

import com.example.transpapptest.config.common.ApiResponse
import com.example.transpapptest.data.remote.payload.response.MessageResponse
import com.example.transpapptest.domain.repository.AddressRepository
import kotlinx.coroutines.flow.Flow

class DeleteAddress(
    private val repository: AddressRepository
) {
    suspend operator fun invoke(addressId: Long): Flow<ApiResponse<MessageResponse>> {
        return repository.deleteAddress(addressId)
    }
}