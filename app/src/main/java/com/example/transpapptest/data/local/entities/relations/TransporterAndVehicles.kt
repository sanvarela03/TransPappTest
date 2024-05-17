package com.example.transpapptest.data.local.entities.relations

import androidx.room.Embedded
import androidx.room.Relation
import com.example.transpapptest.data.local.entities.AddressEntity
import com.example.transpapptest.data.local.entities.CustomerEntity
import com.example.transpapptest.data.local.entities.TransporterEntity
import com.example.transpapptest.data.local.entities.VehicleEntity

data class TransporterAndVehicles(
    @Embedded val transporter: TransporterEntity,
    @Relation(
        parentColumn = "transporterId",
        entityColumn = "transporterId"
    )
    val vehicleList: List<VehicleEntity>
)
