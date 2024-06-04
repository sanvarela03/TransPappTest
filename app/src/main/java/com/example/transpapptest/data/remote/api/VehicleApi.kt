package com.example.transpapptest.data.remote.api

import com.example.transpapptest.data.remote.payload.request.VehicleRequest
import com.example.transpapptest.data.remote.payload.response.MessageResponse
import com.example.transpapptest.data.remote.payload.response.transporter.vehicle.VehicleResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface VehicleApi {
    @POST("/v1/api/transporters/{transporterId}/vehicles")
    suspend fun add(
        @Path("transporterId") transporterId: Long,
        @Body vehicleRequest: VehicleRequest
    ): Response<MessageResponse>

    @PUT("/v1/api/transporters/{transporterId}/vehicles/{vehicleId}")
    suspend fun update(): Response<MessageResponse>

    @GET("/v1/api/transporters/{transporterId}/vehicles")
    suspend fun getAll(@Path("transporterId") transporterId: Long): Response<List<VehicleResponse>>

    @DELETE("")
    suspend fun delete(): Response<MessageResponse>
}