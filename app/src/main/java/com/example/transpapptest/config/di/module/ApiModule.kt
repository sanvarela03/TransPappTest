package com.example.transpapptest.config.di.module

import com.example.transpapptest.data.remote.api.AddressApi
import com.example.transpapptest.data.remote.api.AuthApi
import com.example.transpapptest.data.remote.api.CustomerApi
import com.example.transpapptest.data.remote.api.TransporterApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class ApiModule {
    @Provides
    @Singleton
    fun provideAuthApi(
        okHttpClient: OkHttpClient,
        retrofit: Retrofit.Builder
    ): AuthApi {
        return retrofit.client(okHttpClient).build().create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCustomerApi(
        okHttpClient: OkHttpClient,
        retrofit: Retrofit.Builder
    ): CustomerApi {
        return retrofit.client(okHttpClient).build().create(CustomerApi::class.java)
    }

    @Provides
    @Singleton
    fun provideTransporterApi(
        okHttpClient: OkHttpClient,
        retrofit: Retrofit.Builder
    ): TransporterApi {
        return retrofit.client(okHttpClient).build().create(TransporterApi::class.java)
    }


    @Provides
    @Singleton
    fun provideAddressApi(
        okHttpClient: OkHttpClient,
        retrofit: Retrofit.Builder
    ): AddressApi {
        return retrofit.client(okHttpClient).build().create(AddressApi::class.java)
    }

}