package com.example.transpapptest.data.local.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.transpapptest.data.local.entities.ProducerEntity
import com.example.transpapptest.data.local.entities.ProductEntity

data class ProducerAndProducts(
    @Embedded val producer: ProducerEntity,
    @Relation(
        parentColumn = "producerId",
        entityColumn = "producerId"
    )
    val productList: List<ProductEntity>
)
