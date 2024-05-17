package com.example.transpapptest.domain.use_cases.producer

import com.example.transpapptest.config.common.ApiResponse
import com.example.transpapptest.data.local.entities.relations.ProducerAndAddress
import com.example.transpapptest.data.local.entities.relations.ProducerAndProducts
import com.example.transpapptest.domain.repository.ProducerRepository
import kotlinx.coroutines.flow.Flow

class SearchProducer(
    private val repository: ProducerRepository
) {
    suspend operator fun invoke(
        producerId: Long,
        fetchFromRemote: Boolean
    ): Flow<ApiResponse<ProducerAndProducts>> {
        return repository.searchProducer(producerId, fetchFromRemote)
    }
}