package com.comp491.investsmart.domain.users.usecases

import com.comp491.investsmart.data.api.Result

interface SignUpUseCase {

    suspend operator fun invoke(username: String, email: String, password: String): Result<String>
}