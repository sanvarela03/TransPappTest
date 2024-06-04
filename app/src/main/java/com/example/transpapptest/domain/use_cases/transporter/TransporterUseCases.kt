package com.example.transpapptest.domain.use_cases.transporter

data class TransporterUseCases(
    val loadTransporter: LoadTransporter,
    val getTransporter: GetTransporter,
    val getAll : GetAll,
    val updateAvailability : UpdateAvailability,
    val updateLocalTransporter : UpdateLocalTransporter,
    val clearLocalTransporter : ClearLocalTransporter
)
