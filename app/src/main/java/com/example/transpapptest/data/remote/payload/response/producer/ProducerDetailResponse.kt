package com.example.transpapptest.data.remote.payload.response.producer

data class ProducerDetailResponse(
    val producerSearchResponse: ProducerSearchResponse,
    val productList: List<ProductResponse>
)
