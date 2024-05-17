package com.example.transpapptest.domain.use_cases.transporter

import com.example.transpapptest.data.local.entities.TransporterEntity
import com.example.transpapptest.domain.repository.TransporterRepository
import kotlinx.coroutines.flow.Flow

class GetAll(
    private val repository: TransporterRepository
) {
    suspend operator fun invoke(): List<TransporterEntity> {
        return repository.getAll()
    }
}