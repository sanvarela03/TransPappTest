package com.example.transpapptest.data.local.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.transpapptest.data.local.entities.CustomerInfoEntity
import com.example.transpapptest.data.local.entities.OrderEntity
import com.example.transpapptest.data.local.entities.ProducerInfoEntity

data class OrderAndProducerInfo(
    @Embedded val order: OrderEntity,
    @Relation(
        parentColumn = "orderId",
        entityColumn = "orderId"
    )
    val producerInfoEntity: ProducerInfoEntity
)
