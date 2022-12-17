package com.comp491.investsmart.domain.users.usecases.internal

import com.comp491.investsmart.data.api.Result
import com.comp491.investsmart.data.datastore.DataStoreManager
import com.comp491.investsmart.domain.users.entities.User
import com.comp491.investsmart.domain.users.repositories.UsersRepository
import com.comp491.investsmart.domain.users.usecases.SignUpUseCase
import javax.inject.Inject

class SignUpUseCaseImpl @Inject constructor(
    private val usersRepository: UsersRepository,
    private val dataStoreManager: DataStoreManager,
): SignUpUseCase {

    override suspend fun invoke(username: String, email: String, password: String): Result<String> {
        val result = usersRepository.signUp(
            User(
                username = username,
                email = email,
                password = password,
            )
        )

        if (result is Result.Success) {
            dataStoreManager.setEmail(email = email)
            dataStoreManager.setUsername(username = username)

            if (result.data != null && result.data != "") {
                dataStoreManager.setToken(token = result.data)
            }
        }

        return result
    }
}
