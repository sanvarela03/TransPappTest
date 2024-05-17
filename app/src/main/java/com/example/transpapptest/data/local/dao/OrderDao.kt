package com.example.transpapptest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.transpapptest.data.local.entities.OrderEntity

@Dao
interface OrderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrder(orderEntity: OrderEntity)

    @Transaction
    @Query("SELECT * FROM OrderEntity WHERE orderId = :orderId")
    suspend fun getOrder(orderId: Long): OrderEntity?

    @Transaction
    @Query("DELETE FROM OrderEntity")
    suspend fun clearOrderEntity()
}