package com.example.transpapptest.config.di.module

import com.example.transpapptest.domain.use_cases.customer.CustomerUseCases
import com.example.transpapptest.ui.viewmodels.HomeViewModel
import com.example.transpapptest.ui.viewmodels.SignInViewModel
import com.example.transpapptest.ui.viewmodels.AuthViewModel
import com.example.transpapptest.domain.use_cases.auth.AuthUseCases
import com.example.transpapptest.domain.use_cases.transporter.TransporterUseCases
import com.example.transpapptest.security.TokenManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ViewModelModule {

    @Provides
    @Singleton
    fun provideSplashViewModel(authUseCases: AuthUseCases): AuthViewModel {
        return AuthViewModel(authUseCases)
    }

    @Provides
    @Singleton
    fun provideSignInViewModel(
        authUseCases: AuthUseCases,
        tokenManager: TokenManager,
        authViewModel: AuthViewModel
    ): SignInViewModel {

        return SignInViewModel(authUseCases, tokenManager, authViewModel)
    }

    @Provides
    @Singleton
    fun provideHomeViewModel(
        authUseCases: AuthUseCases,
        customerUseCases: CustomerUseCases,
        transporterUseCases: TransporterUseCases,
        tokenManager: TokenManager,
        authViewModel: AuthViewModel,
        signInViewModel: SignInViewModel

    ): HomeViewModel {
        return HomeViewModel(
            authUseCases = authUseCases,
            customerUseCases = customerUseCases,
            transporterUseCases = transporterUseCases,
            tokenManager = tokenManager,
            authViewModel = authViewModel,
            signInViewModel = signInViewModel
        )
    }
}