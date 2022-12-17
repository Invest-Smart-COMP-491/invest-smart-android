package com.comp491.investsmart.domain.users.inject

import com.comp491.investsmart.domain.users.usecases.LogOutUseCase
import com.comp491.investsmart.domain.users.usecases.SignInUseCase
import com.comp491.investsmart.domain.users.usecases.SignUpUseCase
import com.comp491.investsmart.domain.users.usecases.internal.LogOutUseCaseImpl
import com.comp491.investsmart.domain.users.usecases.internal.SignInUseCaseImpl
import com.comp491.investsmart.domain.users.usecases.internal.SignUpUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class HiltUsersModule {

    @Binds
    abstract fun bindLogOutUseCase(
        logOutUseCaseImpl: LogOutUseCaseImpl
    ): LogOutUseCase

    @Binds
    abstract fun bindSignUpUseCase(
        signUpUseCaseImpl: SignUpUseCaseImpl
    ): SignUpUseCase

    @Binds
    abstract fun bindSignInUseCase(
        signInUseCaseImpl: SignInUseCaseImpl
    ): SignInUseCase
}