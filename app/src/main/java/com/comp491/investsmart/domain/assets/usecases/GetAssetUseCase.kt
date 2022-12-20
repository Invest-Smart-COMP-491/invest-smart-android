package com.comp491.investsmart.domain.assets.usecases

import com.comp491.investsmart.data.api.Result
import com.comp491.investsmart.domain.assets.entities.Asset

interface GetAssetUseCase {

    suspend operator fun invoke(assetTicker: String): Result<Asset>
}