package com.example.transpapptest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.transpapptest.data.local.entities.TransporterEntity
import com.example.transpapptest.data.local.entities.VehicleEntity

@Dao
interface VehicleDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertVehicle(vehicleEntity: VehicleEntity)

    @Transaction
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllVehicles(vehicleEntity: List<VehicleEntity>)

    @Transaction
    @Query("SELECT * FROM VehicleEntity WHERE id = :vehicleId")
    suspend fun getVehicle(vehicleId: Long): VehicleEntity?

    @Transaction
    @Query("SELECT * FROM VehicleEntity")
    suspend fun getAllVehicles(): List<VehicleEntity>?

    @Transaction
    @Query("SELECT * FROM VehicleEntity WHERE transporterId = :transporterId")
    suspend fun getAllVehicles(transporterId: Long): List<VehicleEntity>?

    @Transaction
    @Query("DELETE FROM VehicleEntity")
    suspend fun clearVehicleEntity()
}