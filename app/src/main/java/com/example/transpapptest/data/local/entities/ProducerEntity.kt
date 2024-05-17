package com.example.transpapptest.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ProducerEntity(
    @PrimaryKey(autoGenerate = false)
    val producerId: Long,
    val name: String,
    val lastName: String,
    val email: String,
    val phone: String,
    val currentAddressId: Long
)
