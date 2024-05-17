package com.example.transpapptest.ui.states

import com.example.transpapptest.data.local.entities.AddressEntity
import com.example.transpapptest.data.local.entities.relations.OrderAndStatus

data class OrderListState(
    val orderList: List<OrderAndStatus> = emptyList(),
    val isRefreshing: Boolean = false,
)