package com.comp491.investsmart.domain.users.usecases

import com.comp491.investsmart.domain.users.entities.UserInfoType

interface SetUserInfoUseCase {

    suspend operator fun invoke(infoType: UserInfoType, value: String)
}