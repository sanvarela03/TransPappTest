package com.example.transpapptest.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.example.transpapptest.data.local.entities.AddressEntity

@Dao
interface AddressDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAddress(addressEntity: AddressEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllAddresses(addressEntity: List<AddressEntity>)

    @Transaction
    @Query("SELECT * FROM AddressEntity WHERE userId = :userId")
    suspend fun getAddress(userId: Long): AddressEntity?

    @Transaction
    @Query("DELETE FROM AddressEntity")
    suspend fun clearAddressEntity()
}