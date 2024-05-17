package com.example.transpapptest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.transpapptest.data.local.entities.CustomerInfoEntity
import com.example.transpapptest.data.local.entities.TransporterInfoEntity


@Dao
interface TransporterInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransporterInfo(transporterInfoEntity: TransporterInfoEntity)

    @Transaction
    @Query("SELECT * FROM TransporterInfoEntity WHERE orderId = :orderId")
    suspend fun getTransporterInfo(orderId: Long): TransporterInfoEntity?

    @Transaction
    @Query("DELETE FROM TransporterInfoEntity")
    suspend fun clearTransporterInfoEntity()
}