package com.example.transpapptest.data.remote.payload.response.customer.order

import com.example.transpapptest.data.local.entities.StatusEntity

data class StatusResponse(
    val statusId: Long?,
    val name: String,
    val createdAt: String,
    val orderId: Long
) {
    fun toStatusEntity(): StatusEntity {
        return StatusEntity(
            id = null,
            statusId = statusId,
            name = name,
            createdAt = createdAt,
            orderId = orderId
        )
    }
}
