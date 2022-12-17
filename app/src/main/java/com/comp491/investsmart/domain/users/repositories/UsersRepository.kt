package com.comp491.investsmart.domain.users.repositories

import com.comp491.investsmart.data.api.Result
import com.comp491.investsmart.domain.users.entities.User

interface UsersRepository {

    suspend fun logOut(): Result<Unit>
    suspend fun signIn(user: User): Result<String>
    suspend fun signUp(user: User): Result<String>
}
