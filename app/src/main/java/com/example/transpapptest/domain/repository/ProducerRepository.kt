package com.example.transpapptest.domain.repository

import com.example.transpapptest.config.common.ApiResponse
import com.example.transpapptest.data.local.entities.AddressEntity
import com.example.transpapptest.data.local.entities.ProducerEntity
import com.example.transpapptest.data.local.entities.relations.ProducerAndAddress
import com.example.transpapptest.data.local.entities.relations.ProducerAndProducts
import kotlinx.coroutines.flow.Flow

interface ProducerRepository {
    suspend fun searchProducers(
        fetchFromRemote: Boolean
    ): Flow<ApiResponse<List<ProducerAndAddress>>>

    suspend fun searchProducer(
        producerId: Long,
        fetchFromRemote: Boolean
    ): Flow<ApiResponse<ProducerAndProducts>>
}