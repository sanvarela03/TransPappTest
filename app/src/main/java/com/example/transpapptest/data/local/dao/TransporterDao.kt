package com.example.transpapptest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.transpapptest.data.local.entities.CustomerEntity
import com.example.transpapptest.data.local.entities.TransporterEntity
import com.example.transpapptest.data.local.entities.relations.CustomerAndAddress
import com.example.transpapptest.data.local.entities.relations.TransporterAndAddress
import kotlinx.coroutines.flow.Flow

@Dao
interface TransporterDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTransporter(transporterEntity: TransporterEntity)

    @Transaction
    @Query("SELECT * FROM TransporterEntity WHERE transporterId = :transporterId")
    fun getTransporter(transporterId: Long): Flow<TransporterEntity>


    @Transaction
    @Query("SELECT * FROM TransporterEntity")
    suspend fun getAllTransporters(): List<TransporterEntity>

    @Transaction
    @Query("SELECT * FROM TransporterEntity WHERE transporterId = :transporterId")
    fun getLocalTransporter(transporterId: Long): TransporterEntity?

    @Transaction
    @Query("DELETE FROM TransporterEntity")
    suspend fun clearTransporterEntity()

    @Transaction
    @Query("SELECT * FROM TransporterEntity WHERE transporterId = :transporterId")
    suspend fun getTransporterAndAddress(transporterId: Long): List<TransporterAndAddress>
}