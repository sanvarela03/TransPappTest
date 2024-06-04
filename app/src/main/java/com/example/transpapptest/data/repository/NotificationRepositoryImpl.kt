package com.example.transpapptest.data.repository

import com.example.transpapptest.data.local.dao.NotificationDao
import com.example.transpapptest.data.local.entities.NotificationEntity
import com.example.transpapptest.domain.repository.NotificationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NotificationRepositoryImpl @Inject constructor(
    private val dao: NotificationDao
) : NotificationRepository {
    override suspend fun saveNotification(notificationEntity: NotificationEntity) {
        dao.insert(notificationEntity)
    }

    override suspend fun deleteNotification(notificationId: Int) {
        dao.deleteById(notificationId)
    }

    override fun getNotifications(): Flow<List<NotificationEntity>> {
        return dao.getAll()
    }
}