package com.example.transpapptest.config.di.module

import com.example.transpapptest.domain.repository.AddressRepository
import com.example.transpapptest.domain.repository.AuthRepository
import com.example.transpapptest.domain.repository.CustomerRepository
import com.example.transpapptest.domain.repository.OrderRepository
import com.example.transpapptest.domain.repository.ProducerRepository
import com.example.transpapptest.domain.repository.ShoppingCartRepository
import com.example.transpapptest.domain.repository.TransporterRepository
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
import com.example.transpapptest.domain.use_cases.transporter.GetAll
import com.example.transpapptest.domain.use_cases.transporter.GetTransporter
import com.example.transpapptest.domain.use_cases.transporter.LoadTransporter
import com.example.transpapptest.domain.use_cases.transporter.TransporterUseCases
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
            getAll = GetAll(repository)
        )
    }
}