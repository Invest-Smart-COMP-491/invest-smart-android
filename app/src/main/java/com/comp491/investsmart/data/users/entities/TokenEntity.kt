package com.comp491.investsmart.data.users.entities

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class TokenEntity (
    @Json(name = "token")
    val token: String,

    @Json(name = "user")
    val user: ResponseUserEntity,
)
