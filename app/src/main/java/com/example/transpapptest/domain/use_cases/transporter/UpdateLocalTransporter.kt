package com.example.transpapptest.domain.use_cases.transporter

import com.example.transpapptest.data.local.entities.TransporterEntity
import com.example.transpapptest.domain.repository.TransporterRepository

class UpdateLocalTransporter(
    private val repository: TransporterRepository
) {
    suspend operator fun invoke(transporterEntity: TransporterEntity) {
        repository.updateLocalTransporter(transporterEntity)
    }
}