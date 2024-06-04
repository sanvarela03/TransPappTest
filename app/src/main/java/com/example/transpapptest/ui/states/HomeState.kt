package com.example.transpapptest.ui.states

import com.example.transpapptest.data.remote.payload.response.customer.CustomerInfoResponse
import com.example.transpapptest.data.remote.payload.response.transporter.TransporterInfoResponse

data class HomeState(
    var transporterInfoResponse: TransporterInfoResponse? = null,
    val isRefreshing: Boolean = false,
)
