package com.comp491.investsmart.domain.users.usecases.internal

import com.comp491.investsmart.data.datastore.DataStoreManager
import com.comp491.investsmart.domain.users.entities.UserInfoType
import com.comp491.investsmart.domain.users.usecases.SetUserInfoUseCase
import javax.inject.Inject

class SetUserInfoUseCaseImpl @Inject constructor(
    private val dataStoreManager: DataStoreManager,
): SetUserInfoUseCase {

    override suspend fun invoke(infoType: UserInfoType, value: String) {
        when (infoType) {
            UserInfoType.EMAIL -> {
                dataStoreManager.setEmail(email = value)
            }
            UserInfoType.USERNAME -> {
                dataStoreManager.setUsername(username = value)
            }
            UserInfoType.TOKEN -> {
                dataStoreManager.deleteToken()
                dataStoreManager.setToken(token = value)
            }
        }
    }
}
