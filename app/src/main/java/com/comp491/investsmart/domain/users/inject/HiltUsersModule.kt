package com.comp491.investsmart.domain.users.inject

import com.comp491.investsmart.domain.users.usecases.*
import com.comp491.investsmart.domain.users.usecases.internal.*
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

    @Binds
    abstract fun bindGetUserInfoUseCase(
        getUserInfoUseCaseImpl: GetUserInfoUseCaseImpl
    ): GetUserInfoUseCase

    @Binds
    abstract fun bindSetUserInfoUseCase(
        setUserInfoUseCaseImpl: SetUserInfoUseCaseImpl
    ): SetUserInfoUseCase
}
