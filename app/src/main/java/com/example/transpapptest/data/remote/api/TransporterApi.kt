package com.example.transpapptest.data.remote.api

import com.example.transpapptest.data.remote.payload.response.MessageResponse
import com.example.transpapptest.data.remote.payload.response.transporter.TransporterInfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface TransporterApi {
    @GET("/v1/api/transporters/{userId}")
    suspend fun getTransporter(@Path("userId") id: Long): Response<TransporterInfoResponse>

    @PUT("/v1/api/transporters/{userId}")
    suspend fun updateAvailability(
        @Path("userId") id: Long,
        @Query("available") available: Boolean
    ): Response<MessageResponse>
}