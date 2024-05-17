package com.example.transpapptest.domain.use_cases.producer

import com.example.transpapptest.config.common.ApiResponse
import com.example.transpapptest.data.local.entities.AddressEntity
import com.example.transpapptest.data.local.entities.relations.ProducerAndAddress
import com.example.transpapptest.domain.repository.AddressRepository
import com.example.transpapptest.domain.repository.ProducerRepository
import kotlinx.coroutines.flow.Flow

class SearchProducers(
    private val repository: ProducerRepository
) {
    suspend operator fun invoke(fetchFromRemote: Boolean): Flow<ApiResponse<List<ProducerAndAddress>>> {
        return repository.searchProducers(fetchFromRemote)
    }
}