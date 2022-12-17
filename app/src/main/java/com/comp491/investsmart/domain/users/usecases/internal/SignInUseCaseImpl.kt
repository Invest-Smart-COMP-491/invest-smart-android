package com.comp491.investsmart.domain.users.usecases.internal

import com.comp491.investsmart.domain.users.repositories.UsersRepository
import com.comp491.investsmart.domain.users.usecases.SignInUseCase
import javax.inject.Inject
import com.comp491.investsmart.data.api.Result
import com.comp491.investsmart.data.datastore.DataStoreManager
import com.comp491.investsmart.domain.users.entities.User

class SignInUseCaseImpl @Inject constructor(
    private val usersRepository: UsersRepository,
    private val dataStoreManager: DataStoreManager,
): SignInUseCase {

    override suspend fun invoke(username: String, password: String): Result<String> {
        val result = usersRepository.signIn(
            User(
                username = username,
                email = null,
                password = password,
            )
        )

        if (result is Result.Success) {
            if (result.data != null && result.data != "") {
                dataStoreManager.setToken(token = result.data)
            }
        }

        return result
    }
}
