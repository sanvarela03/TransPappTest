package com.example.transpapptest.domain.use_cases.address

import com.example.transpapptest.data.local.entities.AddressEntity
import com.example.transpapptest.domain.repository.AddressRepository

class GetAddress(
    private val repository: AddressRepository
) {
    suspend operator fun invoke(addressId: Long): AddressEntity? {
        return repository.getAddressById(addressId)
    }

}