package com.example.transpapptest.ui.states

import com.example.transpapptest.data.local.entities.AddressEntity
import com.example.transpapptest.data.local.entities.relations.ProducerAndAddress

data class ProducerSearchState(
    val producerList: List<ProducerAndAddress> = emptyList(),
    val isRefreshing: Boolean = false,
)
