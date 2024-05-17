package com.example.transpapptest.ui.states

import com.example.transpapptest.data.local.entities.AddressEntity

data class AddressListState(
    val addressList: List<AddressEntity> = emptyList(),
    val isRefreshing: Boolean = false,
)
