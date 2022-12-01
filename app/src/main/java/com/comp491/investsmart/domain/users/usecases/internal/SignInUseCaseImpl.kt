package com.comp491.investsmart.domain.users.usecases.internal

import com.comp491.investsmart.domain.users.repositories.UsersRepository
import com.comp491.investsmart.domain.users.usecases.SignInUseCase
import javax.inject.Inject

class SignInUseCaseImpl @Inject constructor(
    private val usersRepository: UsersRepository,
): SignInUseCase {

    override suspend fun invoke(email: String, password: String) {
        return usersRepository.signIn(email = email, password = password)
    }
}