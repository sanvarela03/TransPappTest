package com.example.transpapptest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.transpapptest.data.local.entities.CustomerInfoEntity
import com.example.transpapptest.data.local.entities.ProducerInfoEntity

@Dao
interface CustomerInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCustomerInfo(customerInfoEntity: CustomerInfoEntity)

    @Transaction
    @Query("SELECT * FROM CustomerInfoEntity WHERE orderId = :orderId")
    suspend fun getCustomerInfo(orderId: Long): CustomerInfoEntity?

    @Transaction
    @Query("DELETE FROM CustomerInfoEntity")
    suspend fun clearCustomerInfoEntity()
}