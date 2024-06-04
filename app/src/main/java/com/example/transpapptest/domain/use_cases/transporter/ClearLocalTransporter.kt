package com.example.transpapptest.domain.use_cases.transporter

import com.example.transpapptest.domain.repository.TransporterRepository

class ClearLocalTransporter(
    private val repository: TransporterRepository
) {
    suspend operator fun invoke() {
        repository.clearLocalTransporter()
    }
}