package com.comp491.investsmart.data.users.entities

import androidx.annotation.Keep
import com.squareup.moshi.Json

@Keep
data class UserEntity (
    @Json(name = "asset_name")
    val assetName: String,
)