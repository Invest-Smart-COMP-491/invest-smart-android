package com.comp491.investsmart.domain.users.usecases

import com.comp491.investsmart.data.api.Result

interface LogOutUseCase {

    suspend operator fun invoke(): Result<Unit>
}
