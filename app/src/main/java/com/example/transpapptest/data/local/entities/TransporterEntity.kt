package com.example.transpapptest.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TransporterEntity(
    @PrimaryKey(autoGenerate = false)
    val transporterId: Long,
    val username: String,
    val name: String,
    val lastname: String,
    val email: String,
    val currentVehicleId: Long,
    val currentAddressId: Long,
)
