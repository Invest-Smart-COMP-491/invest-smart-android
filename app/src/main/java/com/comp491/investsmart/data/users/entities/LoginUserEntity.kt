package com.comp491.investsmart.data.users.entities

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class LoginUserEntity(
    @Json(name = "username")
    val username: String,

    @Json(name = "password")
    val password: String,
)
