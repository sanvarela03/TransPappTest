package com.example.transpapptest.ui.states

import com.example.transpapptest.data.local.entities.ProducerEntity
import com.example.transpapptest.data.local.entities.ProductEntity

data class ProducerDetailState(
    val producer: ProducerEntity? = null,
    val currentProducerId: Long = -1L,
    val productsList: List<ProductEntity> = emptyList(),
    val isRefreshing: Boolean = false,
)
