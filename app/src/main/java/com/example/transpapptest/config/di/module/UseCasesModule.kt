package com.example.transpapptest.config.di.module

import com.example.transpapptest.domain.repository.AddressRepository
import com.example.transpapptest.domain.repository.AuthRepository
import com.example.transpapptest.domain.repository.CustomerRepository
import com.example.transpapptest.domain.repository.NotificationRepository
import com.example.transpapptest.domain.repository.OrderRepository
import com.example.transpapptest.domain.repository.ProducerRepository
import com.example.transpapptest.domain.repository.ShoppingCartRepository
import com.example.transpapptest.domain.repository.TransporterRepository
import com.example.transpapptest.domain.repository.VehicleRepository
import com.example.transpapptest.domain.use_cases.address.AddAddress
import com.example.transpapptest.domain.use_cases.address.AddressUseCases
import com.example.transpapptest.domain.use_cases.address.DeleteAddress
import com.example.transpapptest.domain.use_cases.address.GetAddress
import com.example.transpapptest.domain.use_cases.address.GetAllAddresses
import com.example.transpapptest.domain.use_cases.address.UpdateAddress
import com.example.transpapptest.domain.use_cases.customer.CustomerUseCases
import com.example.transpapptest.domain.use_cases.customer.GetCustomer
import com.example.transpapptest.domain.use_cases.order.AddOrder
import com.example.transpapptest.domain.use_cases.order.GetAllOrders
import com.example.transpapptest.domain.use_cases.order.OrderUseCases
import com.example.transpapptest.domain.use_cases.producer.ProducerUseCases
import com.example.transpapptest.domain.use_cases.producer.SearchProducer
import com.example.transpapptest.domain.use_cases.producer.SearchProducers
import com.example.transpapptest.domain.use_cases.shoping_cart.AddShoppingCartItem
import com.example.transpapptest.domain.use_cases.shoping_cart.DeleteShoppingCartItem
import com.example.transpapptest.domain.use_cases.shoping_cart.GetProducerAndShoppingCart
import com.example.transpapptest.domain.use_cases.shoping_cart.GetShoppingCartItems
import com.example.transpapptest.domain.use_cases.shoping_cart.ShoppingCartUseCases
import com.example.transpapptest.domain.use_cases.auth.AuthUseCases
import com.example.transpapptest.domain.use_cases.auth.Authenticate
import com.example.transpapptest.domain.use_cases.auth.GetUserId
import com.example.transpapptest.domain.use_cases.auth.SignIn
import com.example.transpapptest.domain.use_cases.auth.SignOut
import com.example.transpapptest.domain.use_cases.auth.SignUp
import com.example.transpapptest.domain.use_cases.notifications.DeleteNotification
import com.example.transpapptest.domain.use_cases.notifications.GetNotifications
import com.example.transpapptest.domain.use_cases.notifications.NotificationsUseCases
import com.example.transpapptest.domain.use_cases.notifications.SaveNotification
import com.example.transpapptest.domain.use_cases.transporter.ClearLocalTransporter
import com.example.transpapptest.domain.use_cases.transporter.GetAll
import com.example.transpapptest.domain.use_cases.transporter.GetTransporter
import com.example.transpapptest.domain.use_cases.transporter.LoadTransporter
import com.example.transpapptest.domain.use_cases.transporter.TransporterUseCases
import com.example.transpapptest.domain.use_cases.transporter.UpdateAvailability
import com.example.transpapptest.domain.use_cases.transporter.UpdateLocalTransporter
import com.example.transpapptest.domain.use_cases.vehicle.AddVehicle
import com.example.transpapptest.domain.use_cases.vehicle.GetAllVehicles
import com.example.transpapptest.domain.use_cases.vehicle.GetVehicle
import com.example.transpapptest.domain.use_cases.vehicle.VehicleUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCasesModule {
    @Provides
    @Singleton
    fun provideAuthUseCases(repository: AuthRepository): AuthUseCases {
        return AuthUseCases(
            signIn = SignIn(repository),
            signOut = SignOut(repository),
            signUp = SignUp(repository),
            authenticate = Authenticate(repository),
            getUserId = GetUserId(repository)
        )
    }

    @Provides
    @Singleton
    fun provideCustomerUseCases(repository: CustomerRepository): CustomerUseCases {
        return CustomerUseCases(
            getCustomer = GetCustomer(repository)
        )
    }

    @Provides
    @Singleton
    fun provideAddressUseCases(repository: AddressRepository): AddressUseCases {
        return AddressUseCases(
            addAddress = AddAddress(repository),
            deleteAddress = DeleteAddress(repository),
            getAddress = GetAddress(repository),
            updateAddress = UpdateAddress(repository),
            getAllAddresses = GetAllAddresses(repository)
        )
    }


    @Provides
    @Singleton
    fun provideOrderUseCases(repository: OrderRepository): OrderUseCases {
        return OrderUseCases(
            getAllOrders = GetAllOrders(repository),
            addOrder = AddOrder(repository)
        )
    }

    @Provides
    @Singleton
    fun provideProduceUseCases(repository: ProducerRepository): ProducerUseCases {
        return ProducerUseCases(
            searchProducers = SearchProducers(repository),
            searchProducer = SearchProducer(repository)
        )
    }

    @Provides
    @Singleton
    fun provideShoppingCartUseCases(repository: ShoppingCartRepository): ShoppingCartUseCases {
        return ShoppingCartUseCases(
            addShoppingCartItem = AddShoppingCartItem(repository),
            getShoppingCartItems = GetShoppingCartItems(repository),
            deleteShoppingCartItem = DeleteShoppingCartItem(repository),
            getProducerAndShoppingCart = GetProducerAndShoppingCart(repository)
        )
    }

    @Provides
    @Singleton
    fun provideTransporterUseCases(repository: TransporterRepository): TransporterUseCases {
        return TransporterUseCases(
            loadTransporter = LoadTransporter(repository),
            getTransporter = GetTransporter(repository),
            getAll = GetAll(repository),
            updateAvailability = UpdateAvailability(repository),
            updateLocalTransporter = UpdateLocalTransporter(repository),
            clearLocalTransporter = ClearLocalTransporter(repository)
        )
    }

    @Provides
    @Singleton
    fun provideVehicleUseCases(repository: VehicleRepository): VehicleUseCases {
        return VehicleUseCases(
            getAllVehicles = GetAllVehicles(repository),
            getVehicle = GetVehicle(repository),
            addVehicle = AddVehicle(repository)
        )
    }

    @Provides
    @Singleton
    fun provideNotificationsUseCases(repository: NotificationRepository): NotificationsUseCases {
        return NotificationsUseCases(
            saveNotification = SaveNotification(repository),
            getNotifications = GetNotifications(repository),
            deleteNotification = DeleteNotification(repository)
        )
    }
}