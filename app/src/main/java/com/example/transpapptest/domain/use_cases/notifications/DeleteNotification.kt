package com.example.transpapptest.domain.use_cases.notifications


import com.example.transpapptest.domain.repository.NotificationRepository

class DeleteNotification(
    private val repository: NotificationRepository
) {
    suspend operator fun invoke(notificationId: Int) {
        repository.deleteNotification(notificationId)
    }
}