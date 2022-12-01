package com.comp491.investsmart.data.users.inject

import com.comp491.investsmart.data.users.repositories.internal.UsersRepositoryImpl
import com.comp491.investsmart.domain.users.repositories.UsersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class HiltUsersModule {

    @Binds
    abstract fun bindUsersRepository(
        usersRepositoryImpl: UsersRepositoryImpl,
    ): UsersRepository
}
