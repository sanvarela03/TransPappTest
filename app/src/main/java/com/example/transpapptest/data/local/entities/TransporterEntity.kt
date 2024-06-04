package com.example.transpapptest.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.transpapptest.data.remote.payload.response.transporter.TransporterInfoResponse

@Entity
data class TransporterEntity(
    @PrimaryKey(autoGenerate = false)
    val transporterId: Long,
    val username: String,
    val name: String,
    val lastname: String,
    val email: String,
    var available: Boolean,
    val currentVehicleId: Long,
    val currentAddressId: Long,
) {
    fun toTransporterInfoResponse(): TransporterInfoResponse {
        return TransporterInfoResponse(
            transporterId = transporterId,
            username = username,
            name = name,
            lastname = lastname,
            email = email,
            currentAddressId = currentAddressId,
            currentVehicleId = currentVehicleId,
            available = available,
            vehicleList = emptyList(),
            addressList = emptyList(),
            orderInfoResponseList = emptyList()
        )
    }
}
