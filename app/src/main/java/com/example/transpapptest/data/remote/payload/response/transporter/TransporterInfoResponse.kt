package com.example.transpapptest.data.remote.payload.response.transporter

import com.example.transpapptest.data.local.entities.TransporterEntity
import com.example.transpapptest.data.remote.payload.response.customer.address.AddressResponse
import com.example.transpapptest.data.remote.payload.response.customer.order.OrderInfoResponse
import com.example.transpapptest.data.remote.payload.response.transporter.vehicle.VehicleResponse

data class TransporterInfoResponse(
    val transporterId: Long,
    val username: String,
    val name: String,
    val lastname: String,
    val email: String,
    val currentAddressId: Long,
    val currentVehicleId: Long,
    val addressList: List<AddressResponse>,
    val vehicleList: List<VehicleResponse>,
    val orderInfoResponseList: List<OrderInfoResponse>,
) {
    fun toTransporterEntity(): TransporterEntity {
        return TransporterEntity(
            transporterId = transporterId,
            username = username,
            name = name,
            lastname = lastname,
            email = email,
            currentVehicleId = currentVehicleId,
            currentAddressId = currentAddressId,
        )
    }
}
