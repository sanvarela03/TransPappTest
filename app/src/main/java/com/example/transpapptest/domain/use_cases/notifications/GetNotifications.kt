package com.example.transpapptest.domain.use_cases.notifications

import com.example.transpapptest.data.local.entities.NotificationEntity
import com.example.transpapptest.domain.repository.NotificationRepository
import kotlinx.coroutines.flow.Flow

class GetNotifications(
    private val repository: NotificationRepository
) {
    operator fun invoke(): Flow<List<NotificationEntity>> {
        return repository.getNotifications()
    }
}