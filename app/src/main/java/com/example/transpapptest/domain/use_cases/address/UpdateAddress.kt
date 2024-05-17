package com.example.transpapptest.domain.use_cases.address

import android.util.Log
import com.example.transpapptest.config.common.ApiResponse
import com.example.transpapptest.data.remote.payload.request.UpdateAddressRequest
import com.example.transpapptest.data.remote.payload.response.MessageResponse
import com.example.transpapptest.domain.repository.AddressRepository
import kotlinx.coroutines.flow.Flow

class UpdateAddress(
    private val repository: AddressRepository
) {
    suspend operator fun invoke(updateAddressRequest: UpdateAddressRequest): Flow<ApiResponse<MessageResponse>> {
        Log.d("UpdateAddress", "updateAddressRequest = $updateAddressRequest")
        return repository.updateAddress(updateAddressRequest)
    }
}