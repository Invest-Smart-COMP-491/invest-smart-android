package com.comp491.investsmart.domain.users.usecases

interface SignInUseCase {

    suspend operator fun invoke(email: String, password: String)
}