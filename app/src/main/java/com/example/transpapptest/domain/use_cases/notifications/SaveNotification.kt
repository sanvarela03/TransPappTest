package com.example.transpapptest.domain.use_cases.notifications

import com.example.transpapptest.data.local.entities.NotificationEntity
import com.example.transpapptest.domain.repository.NotificationRepository

class SaveNotification(
    private val repository: NotificationRepository
) {
    suspend operator fun invoke(notificationEntity: NotificationEntity) {
        repository.saveNotification(notificationEntity)
    }
}