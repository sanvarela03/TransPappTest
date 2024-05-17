package com.example.transpapptest.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.transpapptest.data.local.dao.AddressDao
import com.example.transpapptest.data.local.dao.CustomerDao
import com.example.transpapptest.data.local.dao.CustomerInfoDao
import com.example.transpapptest.data.local.dao.DeliveryAddressDao
import com.example.transpapptest.data.local.dao.OrderDao
import com.example.transpapptest.data.local.dao.PickupAddressDao
import com.example.transpapptest.data.local.dao.ProducerInfoDao
import com.example.transpapptest.data.local.dao.ShoppingCartDao
import com.example.transpapptest.data.local.dao.TransporterDao
import com.example.transpapptest.data.local.dao.TransporterInfoDao
import com.example.transpapptest.data.local.dao.VehicleDao
import com.example.transpapptest.data.local.entities.AddressEntity
import com.example.transpapptest.data.local.entities.CustomerEntity
import com.example.transpapptest.data.local.entities.CustomerInfoEntity
import com.example.transpapptest.data.local.entities.DeliveryAddressEntity
import com.example.transpapptest.data.local.entities.OrderEntity
import com.example.transpapptest.data.local.entities.PickupAddressEntity
import com.example.transpapptest.data.local.entities.ProducerEntity
import com.example.transpapptest.data.local.entities.ProducerInfoEntity
import com.example.transpapptest.data.local.entities.ProductEntity
import com.example.transpapptest.data.local.entities.ShoppingCartEntity
import com.example.transpapptest.data.local.entities.StatusEntity
import com.example.transpapptest.data.local.entities.TransporterEntity
import com.example.transpapptest.data.local.entities.TransporterInfoEntity
import com.example.transpapptest.data.local.entities.VehicleEntity

@Database(
    entities = [
        CustomerEntity::class,
        AddressEntity::class,
        OrderEntity::class,
        StatusEntity::class,
        CustomerInfoEntity::class,
        ProducerInfoEntity::class,
        TransporterInfoEntity::class,
        DeliveryAddressEntity::class,
        PickupAddressEntity::class,
        ProducerEntity::class,
        ProductEntity::class,
        ShoppingCartEntity::class,
        TransporterEntity::class,
        VehicleEntity::class
    ],
    version = 1
)
abstract class TransporterInfoDatabase : RoomDatabase() {
    abstract val addressDao: AddressDao
    abstract val customerDao: CustomerDao
    abstract val customerInfoDao: CustomerInfoDao
    abstract val deliveryAddressDao: DeliveryAddressDao
    abstract val orderDao: OrderDao
    abstract val pickupAddressDao: PickupAddressDao
    abstract val producerInfoDao: ProducerInfoDao
    abstract val shoppingCartDao: ShoppingCartDao
    abstract val transporterDao: TransporterDao
    abstract val transporterInfoDao: TransporterInfoDao
    abstract val vehicleDao: VehicleDao
}