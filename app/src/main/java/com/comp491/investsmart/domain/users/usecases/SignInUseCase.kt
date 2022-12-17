package com.comp491.investsmart.domain.users.usecases

import com.comp491.investsmart.data.api.Result

interface SignInUseCase {

    suspend operator fun invoke(username: String, password: String): Result<String>
}