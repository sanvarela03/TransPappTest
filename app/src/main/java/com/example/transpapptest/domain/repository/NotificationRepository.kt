package com.example.transpapptest.domain.repository

import com.example.transpapptest.data.local.entities.NotificationEntity
import kotlinx.coroutines.flow.Flow

interface NotificationRepository {
    suspend fun saveNotification(notificationEntity: NotificationEntity)
    suspend fun deleteNotification(notificationId: Int)
    fun getNotifications(): Flow<List<NotificationEntity>>
}