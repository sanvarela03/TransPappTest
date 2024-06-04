package com.example.transpapptest.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class NotificationEntity(
    @PrimaryKey
    val id: Int?,
    val title: String,
    val body: String,
    val createdAt: String
)