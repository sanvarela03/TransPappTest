package com.example.transpapptest.config.di.module

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
import com.example.transpapptest.data.remote.api.AddressApi
import com.example.transpapptest.data.remote.api.AuthApi
import com.example.transpapptest.data.remote.api.CustomerApi
import com.example.transpapptest.data.remote.api.TransporterApi
import com.example.transpapptest.data.repository.AddressRepositoryImpl
import com.example.transpapptest.data.repository.AuthRepositoryImpl
import com.example.transpapptest.data.repository.CustomerRepositoryImpl
import com.example.transpapptest.data.repository.OrderRepositoryImpl
import com.example.transpapptest.data.repository.ProducerRepositoryImpl
import com.example.transpapptest.data.repository.ShoppingCartRepositoryImpl
import com.example.transpapptest.data.repository.TransporterRepositoryImpl
import com.example.transpapptest.domain.repository.AddressRepository
import com.example.transpapptest.domain.repository.AuthRepository
import com.example.transpapptest.domain.repository.CustomerRepository
import com.example.transpapptest.domain.repository.OrderRepository
import com.example.transpapptest.domain.repository.ProducerRepository
import com.example.transpapptest.domain.repository.ShoppingCartRepository
import com.example.transpapptest.domain.repository.TransporterRepository
import com.example.transpapptest.security.TokenManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Provides
    @Singleton
    fun provideAuthRepository(
        api: AuthApi,
        tokenManager: TokenManager
    ): AuthRepository {
        return AuthRepositoryImpl(api, tokenManager)
    }

    @Provides
    @Singleton
    fun provideCustomerRepository(
        api: CustomerApi,
        dao: CustomerDao
    ): CustomerRepository {
        return CustomerRepositoryImpl(
            api,
            dao
        )
    }


    @Provides
    @Singleton
    fun provideTransporterRepository(
        api: TransporterApi,
        transporterDao: TransporterDao,
        addressDao: AddressDao,
        vehicleDao: VehicleDao,
        orderDao: OrderDao,
        producerInfoDao: ProducerInfoDao,
        customerInfoDao: CustomerInfoDao,
        transporterInfoDao: TransporterInfoDao,
        deliveryAddressDao: DeliveryAddressDao,
        pickupAddressDao: PickupAddressDao,
        tokenManager: TokenManager
    ): TransporterRepository {
        return TransporterRepositoryImpl(
            transporterApi = api,
            transporterDao = transporterDao,
            addressDao = addressDao,
            vehicleDao = vehicleDao,
            orderDao = orderDao,
            producerInfoDao = producerInfoDao,
            customerInfoDao = customerInfoDao,
            transporterInfoDao = transporterInfoDao,
            deliveryAddressDao = deliveryAddressDao,
            pickupAddressDao = pickupAddressDao,
            tokenManager = tokenManager
        )
    }

    @Provides
    @Singleton
    fun provideAddressRepository(
        api: AddressApi,
        dao: CustomerDao,
        transporterDao: TransporterDao,
        tokenManager: TokenManager
    ): AddressRepository {
        return AddressRepositoryImpl(
            api,
            dao,
            transporterDao,
            tokenManager
        )
    }

    @Provides
    @Singleton
    fun provideOrderRepository(
        dao: CustomerDao,
        api: CustomerApi,
        tokenManager: TokenManager
    ): OrderRepository {
        return OrderRepositoryImpl(
            dao,
            api,
            tokenManager
        )
    }


    @Provides
    @Singleton
    fun provideProducerRepository(
        dao: CustomerDao,
        api: CustomerApi,
        tokenManager: TokenManager
    ): ProducerRepository {
        return ProducerRepositoryImpl(
            tokenManager,
            api,
            dao,
        )
    }


    @Provides
    @Singleton
    fun provideShoppingCartRepository(
        dao: CustomerDao,
        shoppingCartDao: ShoppingCartDao
    ): ShoppingCartRepository {
        return ShoppingCartRepositoryImpl(
            dao,
            shoppingCartDao
        )
    }

}