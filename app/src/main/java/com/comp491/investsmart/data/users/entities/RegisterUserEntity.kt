package com.comp491.investsmart.data.users.entities

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class RegisterUserEntity(
    @Json(name = "username")
    val username: String,

    @Json(name = "email")
    val email: String,

    @Json(name = "password")
    val password: String,
)
