package com.example.transpapptest.ui.states

import com.example.transpapptest.data.remote.payload.response.customer.CustomerInfoResponse

data class HomeState(
    var customerInfoResponse: CustomerInfoResponse? = null,
    val isRefreshing: Boolean = false,
)
