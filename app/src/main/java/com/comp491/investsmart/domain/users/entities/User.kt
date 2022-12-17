package com.comp491.investsmart.domain.users.entities

import com.comp491.investsmart.data.users.entities.LoginUserEntity
import com.comp491.investsmart.data.users.entities.RegisterUserEntity

data class User(
    val username: String,
    val email: String? = null,
    val password: String,
)

fun User.toLoginUserEntity(): LoginUserEntity {
    return LoginUserEntity(
        username = username,
        password = password,
    )
}

fun User.toRegisterUserEntity(): RegisterUserEntity {
    return RegisterUserEntity(
        username = username,
        email = email ?: "",
        password = password,
    )
}
