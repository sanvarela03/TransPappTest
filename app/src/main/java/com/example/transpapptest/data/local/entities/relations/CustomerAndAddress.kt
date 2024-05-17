package com.example.transpapptest.data.local.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.transpapptest.data.local.entities.AddressEntity
import com.example.transpapptest.data.local.entities.CustomerEntity

data class CustomerAndAddress(
    @Embedded val customer: CustomerEntity,
    @Relation(
        parentColumn = "customerId",
        entityColumn = "userId"
    )
    val addressList: List<AddressEntity>

)
