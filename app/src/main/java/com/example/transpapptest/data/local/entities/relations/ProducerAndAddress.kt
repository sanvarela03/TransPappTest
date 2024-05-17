package com.example.transpapptest.data.local.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.transpapptest.data.local.entities.AddressEntity
import com.example.transpapptest.data.local.entities.ProducerEntity

data class ProducerAndAddress(
    @Embedded val producer: ProducerEntity,
    @Relation(
        parentColumn = "producerId",
        entityColumn = "userId",

    )
    val currentAddress: AddressEntity?
)
