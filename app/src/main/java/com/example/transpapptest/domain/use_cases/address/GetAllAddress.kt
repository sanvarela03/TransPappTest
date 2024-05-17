package com.example.transpapptest.domain.use_cases.address

import com.example.transpapptest.config.common.ApiResponse
import com.example.transpapptest.data.local.entities.AddressEntity
import com.example.transpapptest.domain.repository.AddressRepository
import kotlinx.coroutines.flow.Flow

class GetAllAddresses(
    private val repository: AddressRepository
) {
    suspend operator fun invoke(fetchFromRemote: Boolean): Flow<ApiResponse<List<AddressEntity>>> {
        return repository.getAllAddresses(fetchFromRemote)
    }
}