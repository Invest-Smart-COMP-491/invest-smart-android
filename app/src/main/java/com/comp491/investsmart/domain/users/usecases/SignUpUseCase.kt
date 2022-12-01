package com.comp491.investsmart.domain.users.usecases

interface SignUpUseCase {

    suspend operator fun invoke(username: String, email: String, password: String)
}