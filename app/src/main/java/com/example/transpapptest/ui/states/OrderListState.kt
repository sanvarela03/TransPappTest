package com.example.transpapptest.ui.states

import com.example.transpapptest.data.local.entities.AddressEntity
import com.example.transpapptest.data.local.entities.relations.OrderAndStatus
import com.example.transpapptest.data.remote.payload.response.transporter.TransporterInfoResponse

data class OrderListState(
    val orderList: List<OrderAndStatus> = emptyList(),
    val isRefreshing: Boolean = false,
    val transporterInfoResponse: TransporterInfoResponse? = null
)