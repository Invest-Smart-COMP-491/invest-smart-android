package com.comp491.investsmart.domain.users.usecases.internal

import com.comp491.investsmart.domain.users.repositories.UsersRepository
import com.comp491.investsmart.domain.users.usecases.LogOutUseCase
import javax.inject.Inject

class LogOutUseCaseImpl @Inject constructor(
    private val usersRepository: UsersRepository,
): LogOutUseCase {

    override suspend fun invoke() {
        return usersRepository.logOut()
    }
}