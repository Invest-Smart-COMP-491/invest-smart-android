package com.comp491.investsmart.domain.users.usecases.internal

import com.comp491.investsmart.data.datastore.DataStoreManager
import com.comp491.investsmart.domain.users.entities.UserInfoType
import com.comp491.investsmart.domain.users.usecases.GetUserInfoUseCase
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class GetUserInfoUseCaseImpl @Inject constructor(
    private val dataStoreManager: DataStoreManager,
): GetUserInfoUseCase {

    override suspend fun invoke(infoType: UserInfoType): String {
        return when (infoType) {
            UserInfoType.EMAIL -> {
                dataStoreManager.email.first()
            }
            UserInfoType.USERNAME -> {
                dataStoreManager.username.first()
            }
            UserInfoType.TOKEN -> {
                dataStoreManager.token.first()
            }
        }
    }
}
