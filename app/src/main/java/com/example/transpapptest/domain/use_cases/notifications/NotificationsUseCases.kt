package com.example.transpapptest.domain.use_cases.notifications

data class NotificationsUseCases(
    val saveNotification: SaveNotification,
    val getNotifications: GetNotifications,
    val deleteNotification: DeleteNotification
)