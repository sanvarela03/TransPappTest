package com.example.transpapptest.data.remote.api

import com.example.transpapptest.data.remote.payload.response.transporter.TransporterInfoResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TransporterApi {
    @GET("/v1/api/transporters/{userId}")
    suspend fun getTransporter(@Path("userId") id: Long): Response<TransporterInfoResponse>
}