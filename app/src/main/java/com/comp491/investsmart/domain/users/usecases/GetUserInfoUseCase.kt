package com.comp491.investsmart.domain.users.usecases

import com.comp491.investsmart.domain.users.entities.UserInfoType

interface GetUserInfoUseCase {

    suspend operator fun invoke(infoType: UserInfoType): String
}