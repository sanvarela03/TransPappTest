package com.example.transpapptest.data.local.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.transpapptest.data.local.entities.ProducerEntity
import com.example.transpapptest.data.local.entities.ShoppingCartEntity

data class ProducerAndShoppingCart(
    @Embedded val producer: ProducerEntity,
    @Relation(
        parentColumn = "producerId",
        entityColumn = "producerId"
    )
    val shoppingItems: List<ShoppingCartEntity>
)
