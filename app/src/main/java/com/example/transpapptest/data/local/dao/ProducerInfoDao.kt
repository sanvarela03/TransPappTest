package com.example.transpapptest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.transpapptest.data.local.entities.OrderEntity
import com.example.transpapptest.data.local.entities.ProducerInfoEntity

@Dao
interface ProducerInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducerInfo(producerInfoEntity: ProducerInfoEntity)

    @Transaction
    @Query("SELECT * FROM ProducerInfoEntity WHERE orderId = :orderId")
    suspend fun getProducerInfo(orderId: Long): ProducerInfoEntity?

    @Transaction
    @Query("DELETE FROM ProducerInfoEntity")
    suspend fun clearProducerInfoEntity()
}