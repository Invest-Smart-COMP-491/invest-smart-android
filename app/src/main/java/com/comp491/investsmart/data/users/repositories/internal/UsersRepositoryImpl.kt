package com.comp491.investsmart.data.users.repositories.internal

import com.comp491.investsmart.data.api.Result
import com.comp491.investsmart.data.api.retrofit.InvestSmartService
import com.comp491.investsmart.data.api.safeApiCall
import com.comp491.investsmart.data.datastore.DataStoreManager
import com.comp491.investsmart.domain.users.entities.User
import com.comp491.investsmart.domain.users.entities.toLoginUserEntity
import com.comp491.investsmart.domain.users.entities.toRegisterUserEntity
import com.comp491.investsmart.domain.users.repositories.UsersRepository
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class UsersRepositoryImpl @Inject constructor(
    private val investSmartService: InvestSmartService,
    private val dataStoreManager: DataStoreManager,
): UsersRepository {

    override suspend fun logOut(): Result<Unit> {
        return  safeApiCall {
            investSmartService.logout(
                token = dataStoreManager.token.first(),
            )
        }
    }

    override suspend fun signIn(user: User): Result<String> {
        val result = safeApiCall {
            investSmartService.login(
                user = user.toLoginUserEntity(),
            )
        }

        return if (result is Result.Success) {
            Result.Success(data = result.data?.token ?: "")
        } else {
            Result.Error(errorMessage = result.message ?: "Something went wrong")
        }
    }

    override suspend fun signUp(user: User): Result<String> {
        val result =  safeApiCall {
            investSmartService.register(
                user = user.toRegisterUserEntity(),
            )
        }

        return if (result is Result.Success) {
            Result.Success(data = result.data?.token ?: "")
        } else {
            Result.Error(errorMessage = result.message ?: "Something went wrong")
        }
    }
}
