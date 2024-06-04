package com.example.transpapptest.config.di.module

import android.app.Application
import androidx.room.Room
import com.example.transpapptest.data.local.dao.AddressDao
import com.example.transpapptest.data.local.dao.CustomerDao
import com.example.transpapptest.data.local.dao.CustomerInfoDao
import com.example.transpapptest.data.local.dao.DeliveryAddressDao
import com.example.transpapptest.data.local.dao.NotificationDao
import com.example.transpapptest.data.local.dao.OrderDao
import com.example.transpapptest.data.local.dao.PickupAddressDao
import com.example.transpapptest.data.local.dao.ProducerInfoDao
import com.example.transpapptest.data.local.dao.ShoppingCartDao
import com.example.transpapptest.data.local.dao.TransporterDao
import com.example.transpapptest.data.local.dao.TransporterInfoDao
import com.example.transpapptest.data.local.dao.VehicleDao
import com.example.transpapptest.data.local.db.TransporterInfoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    @Provides
    @Singleton
    fun provideTransporterInfoDatabase(app: Application): TransporterInfoDatabase {
        return Room.databaseBuilder(
            app,
            TransporterInfoDatabase::class.java,
            "transporterinfodb.db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideAddressDao(db: TransporterInfoDatabase): AddressDao {
        return db.addressDao
    }

    @Provides
    @Singleton
    fun provideNotificationDao(db: TransporterInfoDatabase): NotificationDao {
        return db.notificationDao
    }

    @Provides
    @Singleton
    fun provideCustomerDao(db: TransporterInfoDatabase): CustomerDao {
        return db.customerDao
    }

    @Provides
    @Singleton
    fun provideCustomerInfoDao(db: TransporterInfoDatabase): CustomerInfoDao {
        return db.customerInfoDao
    }

    @Provides
    @Singleton
    fun provideDeliveryAddressDao(db: TransporterInfoDatabase): DeliveryAddressDao {
        return db.deliveryAddressDao
    }

    @Provides
    @Singleton
    fun provideOrderDao(db: TransporterInfoDatabase): OrderDao {
        return db.orderDao
    }

    @Provides
    @Singleton
    fun providePickupAddressDao(db: TransporterInfoDatabase): PickupAddressDao {
        return db.pickupAddressDao
    }

    @Provides
    @Singleton
    fun provideProducerInfoDao(db: TransporterInfoDatabase): ProducerInfoDao {
        return db.producerInfoDao
    }

    @Provides
    @Singleton
    fun provideShoppingCartDao(db: TransporterInfoDatabase): ShoppingCartDao {
        return db.shoppingCartDao
    }

    @Provides
    @Singleton
    fun provideTransporterDao(db: TransporterInfoDatabase): TransporterDao {
        return db.transporterDao
    }

    @Provides
    @Singleton
    fun provideTransporterInfoDao(db: TransporterInfoDatabase): TransporterInfoDao {
        return db.transporterInfoDao
    }

    @Provides
    @Singleton
    fun provideVehicleDao(db: TransporterInfoDatabase): VehicleDao {
        return db.vehicleDao
    }
}

