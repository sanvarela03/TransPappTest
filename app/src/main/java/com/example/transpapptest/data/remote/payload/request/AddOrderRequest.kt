package com.example.transpapptest.data.remote.payload.request

data class AddOrderRequest(
    val producerId: Long,
    val deliveryAddressId: Long,
    val paymentMethod: String,
    val products: List<ItemRequest>
)
