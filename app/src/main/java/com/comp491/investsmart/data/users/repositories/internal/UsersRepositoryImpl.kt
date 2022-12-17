package com.comp491.investsmart.data.users.repositories.internal

import com.comp491.investsmart.data.api.retrofit.InvestSmartService
import com.comp491.investsmart.domain.users.repositories.UsersRepository
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val investSmartService: InvestSmartService,
): UsersRepository {

    override suspend fun logOut() {
        TODO("Not yet implemented")
    }

    override suspend fun signIn(email: String, password: String) {
        TODO("Not yet implemented")
    }

    override suspend fun signUp(username: String, email: String, password: String) {
        TODO("Not yet implemented")
    }
}
