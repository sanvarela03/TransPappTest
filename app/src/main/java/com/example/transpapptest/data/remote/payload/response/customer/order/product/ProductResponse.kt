package com.example.transpapptest.data.remote.payload.response.customer.order.product

data class ProductResponse(
    val productId: Long,
    val name: String,
    val description: String,
    val units: Int,
    val price: Double,
)
