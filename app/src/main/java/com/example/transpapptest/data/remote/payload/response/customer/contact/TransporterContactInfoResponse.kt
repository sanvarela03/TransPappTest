package com.example.transpapptest.data.remote.payload.response.customer.contact

data class TransporterContactInfoResponse(
    val transporterId: Long?,
    val completeName: String?,
    val phone: String?,
    val email: String?
)