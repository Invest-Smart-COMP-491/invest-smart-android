package com.comp491.investsmart.domain.users.usecases.internal

import com.comp491.investsmart.domain.users.repositories.UsersRepository
import com.comp491.investsmart.domain.users.usecases.SignUpUseCase
import javax.inject.Inject

class SignUpUseCaseImpl @Inject constructor(
    private val usersRepository: UsersRepository,
): SignUpUseCase {

    override suspend fun invoke(username: String, email: String, password: String) {
        return usersRepository.signUp(username = username, email = email, password = password)
    }
}
